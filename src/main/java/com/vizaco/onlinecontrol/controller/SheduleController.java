package com.vizaco.onlinecontrol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.representation.JournalView;
import com.vizaco.onlinecontrol.service.*;
import com.vizaco.onlinecontrol.utils.DateUtils;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

@Controller
public class SheduleController extends BaseController {

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private Utils utils;

    @Autowired
    @Qualifier("fieldValidator")
    private Validator fieldValidator;

    @Autowired
    private TimeZone timeZone;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat dateFormatJson = new SimpleDateFormat("dd.MM.yyyy/EEEE", new Locale("ru", "Ru"));

    @RequestMapping(value = "/shedules")
    public ModelAndView shedules() {

        ModelAndView mav = new ModelAndView("shedules/shedules");

        List<Shedule> shedules = sheduleService.getSheduleByCriteria(null, null, null, null, null, null);

        mav.addObject("shedules", shedules);

        return mav;
    }

    @RequestMapping(value = "/shedules/studentShedule", method = RequestMethod.GET)
    public ModelAndView studentShedule() {

        ModelAndView mav = new ModelAndView("shedules/studentShedule");
        mav.addObject("students", studentService.getAllStudents());
        return mav;

    }

    @RequestMapping(value = "/shedules/studentShedule", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public String generateStudentShedule(Locale locale, @RequestBody String json) {

        Student student;
        Date startDate;
        Date endDate;

        String badResponse = "{\"result\":\"false\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(timeZone);
        mapper.setDateFormat(dateFormatJson);
        mapper.setLocale(locale);

        Map<String, Object> mapFromJsonElement;
        try {
            mapFromJsonElement = jsonUtil.getMapFromJsonElement(json);
        } catch (IOException e) {
            return badResponse;
        }


        try {
            startDate = formatter.parse((String) mapFromJsonElement.get("startDate"));
            endDate = formatter.parse((String) mapFromJsonElement.get("endDate"));
            student = utils.getStudent((String) mapFromJsonElement.get("student"), new IllegalArgumentException());
        } catch (Exception e) {
            return badResponse;
        }

        List<JournalView> journalViewList = sheduleService.getJournalByCriteria(startDate, endDate, student, null, null, null, null);

        Map<String, Object> resultData = new TreeMap<>();
        for (JournalView journalView : journalViewList) {
            utils.convertToTreeDate(journalView, resultData);
        }

        String jsonResponse;
        try {
            resultData.put("result", "true");
            jsonResponse = mapper.writeValueAsString(resultData);
        } catch (IOException e) {
            return badResponse;
        }

        return jsonResponse;

    }//generateReport

    @RequestMapping(value = "/shedules/studentJournal", method = RequestMethod.GET)
    public ModelAndView studentJournal() {

        ModelAndView mav = new ModelAndView("shedules/studentJournal");
        return mav;

    }

    @RequestMapping(value = "/shedules/studentJournal", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public String generateStudentJournal(Locale locale, @RequestBody String json) {

        Date startDate;
        Date endDate;

        String badResponse = "{\"result\":\"false\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(timeZone);
        mapper.setDateFormat(dateFormatJson);
        mapper.setLocale(locale);

        Map<String, Object> mapFromJsonElement;
        try {
            mapFromJsonElement = jsonUtil.getMapFromJsonElement(json);
        } catch (IOException e) {
            return badResponse;
        }


        try {
            startDate = formatter.parse((String) mapFromJsonElement.get("startDate"));
            endDate = formatter.parse((String) mapFromJsonElement.get("endDate"));
        } catch (Exception e) {
            return badResponse;
        }

        List<JournalView> journalViewList = sheduleService.getJournalByCriteria(startDate, endDate, null, null, null, null, null);

        Map<String, Object> resultData = new TreeMap<>();
        for (JournalView journalView : journalViewList) {
            utils.convertToTreeDateSubjectTeacherPeriod(journalView, resultData);
        }

        String jsonResponse;
        try {
            resultData.put("result", "true");
            jsonResponse = mapper.writeValueAsString(resultData);
        } catch (IOException e) {
            return badResponse;
        }

        return jsonResponse;

    }//generateReport

    @RequestMapping(value = "/shedules/constructor", method = RequestMethod.GET)
    public String registerTemplate(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", periodService.getAllPeriods());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/sheduleConstructor";
    }

    @RequestMapping(value = "/shedules/constructor", method = RequestMethod.POST)
    public String registerTemplate(Locale locale, @RequestBody String json, Model model) {

        if (json == null) {
            model.addAttribute("clazzes", clazzService.getAllClazzes());
            model.addAttribute("periods", periodService.getAllPeriods());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
            model.addAttribute("teachers", teacherService.getAllTeachers());
            model.addAttribute("shedule", new Shedule());
            return "/shedules/sheduleConstructor";
        }

        String[] params = json.split("&");

        TreeSet<Integer> numberOfWeek = new TreeSet<>();

        TreeMap<String, DayOfWeek> daysOfTheWeek = new TreeMap<>();
        TreeMap<String, Period> periods = new TreeMap<>();
        TreeMap<String, Subject> subjects = new TreeMap<>();
        TreeMap<String, Teacher> teachers = new TreeMap<>();

        Calendar startDate = null;
        Calendar endDate = null;

        Clazz clazz = null;

        Integer currentWeek;
        DayOfWeek currentDay;
        Period currentPeriod;
        Subject currentSubject;
        Teacher currentTeacher;

        for (String param : params) {

            if (param.startsWith("dayOfTheWeek")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentDay = DayOfWeek.of(Integer.parseInt(keyValue[1]))) == null) {
                    continue;
                }
                daysOfTheWeek.put(keyValue[0], currentDay);
            } else if (param.startsWith("week_")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || ((currentWeek = Integer.parseInt(keyValue[1])) == null)) {
                    continue;
                }
                numberOfWeek.add(currentWeek);
            } else if (param.startsWith("period")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentPeriod = periodService.findPeriodById(Integer.parseInt(keyValue[1]))) == null) {
                    continue;
                }
                periods.put(keyValue[0], currentPeriod);
            } else if (param.startsWith("subject")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentSubject = subjectService.findSubjectById(Integer.parseInt(keyValue[1]))) == null) {
                    continue;
                }
                subjects.put(keyValue[0], currentSubject);
            } else if (param.startsWith("teacher")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentTeacher = teacherService.findTeacherById(Integer.parseInt(keyValue[1]))) == null) {
                    continue;
                }
                teachers.put(keyValue[0], currentTeacher);
            } else if (param.startsWith("startDate")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1)) {
                    continue;
                }
                startDate = Calendar.getInstance(timeZone, locale);
                try {
                    startDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            } else if (param.startsWith("endDate")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1)) {
                    continue;
                }
                endDate = Calendar.getInstance(timeZone, locale);
                try {
                    endDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            } else if (param.startsWith("classSelect")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1)) {
                    continue;
                }
                clazz = clazzService.findClazzById(Integer.parseInt(keyValue[1]));
            }

        }

        if (startDate == null || endDate == null || clazz == null || numberOfWeek.size() <= 0) {
            model.addAttribute("clazzes", clazzService.getAllClazzes());
            model.addAttribute("periods", periodService.getAllPeriods());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
            model.addAttribute("teachers", teacherService.getAllTeachers());
            model.addAttribute("shedule", new Shedule());
            return "/shedules/sheduleConstructor";
        } else if (startDate.compareTo(endDate) > 0) {
            model.addAttribute("clazzes", clazzService.getAllClazzes());
            model.addAttribute("periods", periodService.getAllPeriods());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
            model.addAttribute("teachers", teacherService.getAllTeachers());
            model.addAttribute("shedule", new Shedule());
            return "/shedules/sheduleConstructor";
        }

        TreeSet<Shedule> shedules = new TreeSet<>();

        Iterator<Integer> iteratorWeek = numberOfWeek.iterator();
        Integer numberWeek = iteratorWeek.next();

        Calendar startDateTemp = Calendar.getInstance(timeZone, locale);
        startDateTemp.setTime(startDate.getTime());

        while (startDateTemp.compareTo(endDate) <= 0) {

            Calendar currentDate = Calendar.getInstance(timeZone, locale);
            currentDate.setTime(startDateTemp.getTime());

            Integer numberDay = dateUtils.getNumberDayOfWeek(startDateTemp);

            for (Map.Entry<String, Subject> keyValue : subjects.entrySet()) {

                String subjectKey = keyValue.getKey();
                Subject subjectValue = keyValue.getValue();

                if (!subjectKey.startsWith("subject_" + numberWeek + "_" + numberDay)) {
                    continue;
                }

                String[] splitSubject = subjectKey.split("_");

                if (emptyValue(splitSubject, 3)) {
                    continue;
                }

                //Find period
                if (!periods.containsKey("period_" + numberWeek + "_" + numberDay + "_" + splitSubject[3])) {
                    continue;
                }
                Period periodValue = periods.get("period_" + numberWeek + "_" + numberDay + "_" + splitSubject[3]);

                //Find teacher
                if (!teachers.containsKey("teacher_" + numberWeek + "_" + numberDay + "_" + splitSubject[3])) {
                    continue;
                }
                Teacher teacherValue = teachers.get("teacher_" + numberWeek + "_" + numberDay + "_" + splitSubject[3]);

                Shedule currentShedule = new Shedule();
                currentShedule.setDate(startDateTemp.getTime());
                currentShedule.setClazz(clazz);
                currentShedule.setPeriod(periodValue);
                currentShedule.setSubject(subjectValue);
                currentShedule.setTeacher(teacherValue);

                shedules.add(currentShedule);

            }

            startDateTemp.add(Calendar.DAY_OF_MONTH, 1);

            if (dateUtils.getNumberDayOfWeek(startDateTemp) == 1) {
                if (!iteratorWeek.hasNext()) {
                    iteratorWeek = numberOfWeek.iterator();
                }
                numberWeek = iteratorWeek.next();
            }

        }

        List<Shedule> shedulesInDB = sheduleService.getSheduleByCriteria(startDate.getTime(), endDate.getTime(), clazz, null, null, null);

        for (Shedule shedule : shedules) {

            for (Shedule sheduleDB : shedulesInDB) {
                if (sheduleDB.getDate().getTime() == shedule.getDate().getTime() & sheduleDB.getPeriod().equals(shedule.getPeriod())) {
                    shedule.setId(sheduleDB.getId());
                }
            }

            sheduleService.saveShedule(shedule);

        }

        return "redirect:/shedules/";
    }

    private boolean emptyValue(String[] keyValue, int position) {
        if (keyValue == null || keyValue.length <= position || "0".equals(keyValue[position]) || "".equals(keyValue[position]) || keyValue[position] == null) {
            return true;
        }
        return false;
    }

    //<editor-fold desc="CRUD SHEDULE">
    //CREATE SHEDULE

    @RequestMapping(value = "/shedules/new", method = RequestMethod.GET)
    public String register(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", periodService.getAllPeriods());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/createOrUpdateSheduleForm";
    }

    @RequestMapping(value = "/shedules/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("shedule") @Valid Shedule shedule, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(shedule);
            model.addAttribute("clazzes", clazzService.getAllClazzes());
            model.addAttribute("periods", periodService.getAllPeriods());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            model.addAttribute("teachers", teacherService.getAllTeachers());
            return "/shedules/createOrUpdateSheduleForm";
        }

        sheduleService.saveShedule(shedule);

        return "redirect:/shedules/";
    }

    //READ CLASS

    @RequestMapping(value = "/shedules/{sheduleId}")
    public ModelAndView read(@PathVariable("sheduleId") String sheduleIdStr) {

        ModelAndView mav = new ModelAndView("/shedules/sheduleDetails");

        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        mav.addObject("shedule", shedule);

        return mav;
    }

    //UPDATE CLASS

    @RequestMapping(value = "/shedules/{sheduleId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("sheduleId") String sheduleIdStr) {

        ModelAndView mav = new ModelAndView("/shedules/createOrUpdateSheduleForm");

        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        mav.addObject("shedule", shedule);

        return mav;
    }

    @RequestMapping(value = "/shedules/{sheduleId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("sheduleId") String sheduleIdStr, @ModelAttribute("shedule") Shedule shedule, BindingResult result, Model model) {

        Shedule sheduleDB = utils.getShedule(sheduleIdStr, null);

        shedule.setId(sheduleDB.getId());
        shedule.setClazz(sheduleDB.getClazz());
        shedule.setDate(sheduleDB.getDate());
        shedule.setGrades(sheduleDB.getGrades());
        shedule.setPeriod(sheduleDB.getPeriod());
        shedule.setSubject(sheduleDB.getSubject());
        shedule.setTeacher(sheduleDB.getTeacher());

        return "redirect:/shedules/";
    }

    //DELETE CLASS

    @RequestMapping(value = "/shedules/{sheduleId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("sheduleId") String sheduleIdStr) {

        return new ModelAndView("redirect:/shedules");

    }

    //</editor-fold>

    @RequestMapping(value = "/shedules/{sheduleId}/students/{studentId}/grades/new", method = RequestMethod.GET)
    public String addGrades(@PathVariable("sheduleId") String sheduleIdStr,
                            @PathVariable("studentId") String studentIdStr, Model model) {
        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);
        Grade grade = new Grade();
        grade.setShedule(shedule);
        grade.setStudent(student);
        model.addAttribute("grade", grade);
        return "/grades/createOrUpdateGradeForm";
    }

    @RequestMapping(value = "/shedules/{sheduleId}/students/{studentId}/grades/new", method = RequestMethod.POST)
    public String addGrades(@PathVariable("sheduleId") String sheduleIdStr,
                            @PathVariable("studentId") String studentIdStr,
                            @ModelAttribute("grade") Grade grade,
                            BindingResult result, Model model) {

        if (grade.getMark() == null & "".equals(grade.getTask())){
            result.rejectValue("", "grade.markOrTaskRequired");
            Shedule shedule = utils.getShedule(sheduleIdStr, null);
            Student student = utils.getStudent(studentIdStr, null);
            grade.setShedule(shedule);
            grade.setStudent(student);

            model.addAttribute("grade", grade);
            return "/grades/createOrUpdateGradeForm";
        }

        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);

        grade.setStudent(student);
        grade.setShedule(shedule);

        gradeService.saveGrade(grade);
        return "redirect:/shedules/studentJournal";
    }

    @RequestMapping(value = "/shedules/{sheduleId}/students/{studentId}/grades/{gradeId}/edit", method = RequestMethod.GET)
    public String editGrades(@PathVariable("sheduleId") String sheduleIdStr,
                             @PathVariable("studentId") String studentIdStr,
                             @PathVariable("gradeId") String gradeIdStr, Model model) {
        Grade grade = utils.getGrade(gradeIdStr, null);
        model.addAttribute("grade", grade);
        return "/grades/createOrUpdateGradeForm";
    }

    @RequestMapping(value = "/shedules/{sheduleId}/students/{studentId}/grades/{gradeId}/edit", method = RequestMethod.PUT)
    public String editGrades(@PathVariable("sheduleId") String sheduleIdStr,
                             @PathVariable("studentId") String studentIdStr,
                             @PathVariable("gradeId") String gradeIdStr,
                             @ModelAttribute("grade") Grade grade) {

        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);
        Grade gradeDB = utils.getGrade(gradeIdStr, null);

        grade.setStudent(student);
        grade.setShedule(shedule);
        grade.setId(gradeDB.getId());

        gradeService.saveGrade(grade);
        return "redirect:/shedules/studentJournal";
    }

    @RequestMapping(value = "/shedules/{sheduleId}/students/{studentId}/grades/{gradeId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteGrades(@PathVariable("sheduleId") String sheduleIdStr, @PathVariable("studentId") String studentIdStr, @PathVariable("gradeId") String gradeIdStr) {

        Grade grade = utils.getGrade(gradeIdStr, null);
        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        Student student = utils.getStudent(studentIdStr, null);
        String badResponse = "{\"result\":\"false\"}";
        String goodResponse = "{\"result\":\"true\"}";

        shedule.getGrades().remove(grade);
        student.getGrades().remove(grade);
        gradeService.deleteGrade(grade.getId());

        return goodResponse;
    }

    @RequestMapping(value = "/shedules/{sheduleId}/job/edit", method = RequestMethod.GET)
    public String editJob(@PathVariable("sheduleId") String sheduleIdStr, Model model) {
        Shedule shedule = utils.getShedule(sheduleIdStr, null);
        model.addAttribute("shedule", shedule);
        return "/shedules/updateJob";
    }

    @RequestMapping(value = "/shedules/{sheduleId}/job/edit", method = RequestMethod.PUT)
    public String editJob(@PathVariable("sheduleId") String sheduleIdStr,
                          @ModelAttribute("shedule") Shedule shedule) {

        Shedule sheduleDB = utils.getShedule(sheduleIdStr, null);

        shedule.setId(sheduleDB.getId());
        shedule.setClazz(sheduleDB.getClazz());
        shedule.setDate(sheduleDB.getDate());
        shedule.setGrades(sheduleDB.getGrades());
        shedule.setPeriod(sheduleDB.getPeriod());
        shedule.setSubject(sheduleDB.getSubject());
        shedule.setTeacher(sheduleDB.getTeacher());

        sheduleService.saveShedule(shedule);
        return "redirect:/shedules/studentJournal";
    }

}
