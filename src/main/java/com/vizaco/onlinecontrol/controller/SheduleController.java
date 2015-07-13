package com.vizaco.onlinecontrol.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.DateUtils;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.zip.GZIPOutputStream;

@Controller
public class SheduleController extends BaseController {

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private JsonUtil jsonUtil;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    @RequestMapping(value = "/shedules")
    public ModelAndView shedules() {

        ModelAndView mav = new ModelAndView("shedules/shedules");

        TreeSet<Shedule> shedules = new TreeSet<>(sheduleService.getAllShedule());

        mav.addObject("shedules", shedules);

        return mav;
    }

    @RequestMapping(value = "/shedules/studentShedule", method = RequestMethod.GET)
    public ModelAndView viewShedule() {

        ModelAndView mav = new ModelAndView("shedules/studentShedule");
        return mav;

    }

    @RequestMapping(value = "/shedules/studentsReport", method = RequestMethod.POST, headers = "content-type=application/json")
    @ResponseBody
    public String generateStudentReport(Locale locale, @RequestBody String json) {

        Date startDate;
        Date endDate;

        String badResponse = "{\"result\":\"false\"}";

        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(formatter);
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
        } catch (ParseException e) {
            return badResponse;
        }

        List<Shedule> sheduleList = sheduleService.getSheduleBeetwenInterval(startDate, endDate);

        String jsonResponse;
        Map<String, Object> resultData = new HashMap<String, Object>();
        try {
            resultData.put("result", "true");
            resultData.put("shedules", sheduleList);
            jsonResponse = mapper.writeValueAsString(resultData);
        } catch (IOException e) {
            return badResponse;
        }

        return jsonResponse;

    }//generateReport

    @RequestMapping(value = "/shedules/constructor", method = RequestMethod.GET)
    public String registerTemplate(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", sheduleService.getAllPeriods());
        model.addAttribute("subjects", sheduleService.getAllSubjects());
        model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
        model.addAttribute("teachers", sheduleService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/sheduleConstructor";
    }

    @RequestMapping(value = "/shedules/constructor", method = RequestMethod.POST)
    public String registerTemplate(@RequestBody String json) {

        if (json == null) {
            return "/shedules/sheduleConstructor";
        }

        String[] params = json.split("&");

        TreeSet<Integer> numberOfWeek = new TreeSet<>();

        TreeMap<String, DayOfWeek> daysOfTheWeek = new TreeMap<>();
        TreeMap<String, Period> periods = new TreeMap<>();
        TreeMap<String, Subject> subjects = new TreeMap<>();
        TreeMap<String, Teacher> teachers = new TreeMap<>();

        GregorianCalendar startDate = null;
        GregorianCalendar endDate = null;

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
                if (emptyValue(keyValue, 1) || (currentPeriod = sheduleService.findPeriodById(Long.parseLong(keyValue[1]))) == null) {
                    continue;
                }
                periods.put(keyValue[0], currentPeriod);
            } else if (param.startsWith("subject")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentSubject = sheduleService.findSubjectById(Long.parseLong(keyValue[1]))) == null) {
                    continue;
                }
                subjects.put(keyValue[0], currentSubject);
            } else if (param.startsWith("teacher")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentTeacher = sheduleService.findTeacherById(Long.parseLong(keyValue[1]))) == null) {
                    continue;
                }
                teachers.put(keyValue[0], currentTeacher);
            } else if (param.startsWith("startDate")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1)) {
                    continue;
                }
                startDate = new GregorianCalendar();
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
                endDate = new GregorianCalendar();
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
                clazz = clazzService.findClazzById(Long.parseLong(keyValue[1]));
            }

        }

        if (startDate == null || endDate == null || clazz == null || numberOfWeek.size() <= 0) {
            return "/shedules/sheduleConstructor";
        } else if (startDate.compareTo(endDate) > 0) {
            return "/shedules/sheduleConstructor";
        }

        TreeSet<Shedule> shedules = new TreeSet<>();

        Iterator<Integer> iteratorWeek = numberOfWeek.iterator();
        Integer numberWeek = iteratorWeek.next();

        Calendar startDateTemp = Calendar.getInstance();
        startDateTemp.setTime(startDate.getTime());

        while (startDateTemp.compareTo(endDate) <= 0) {

            Calendar currentDate = new GregorianCalendar();
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

        List<Shedule> shedulesInDB = sheduleService.getSheduleBeetwenIntervalAndClass(startDate.getTime(), endDate.getTime(), clazz);

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
        model.addAttribute("periods", sheduleService.getAllPeriods());
        model.addAttribute("subjects", sheduleService.getAllSubjects());
        model.addAttribute("teachers", sheduleService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/createOrUpdateSheduleForm";
    }

    @RequestMapping(value = "/shedules/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("shedule") @Valid Shedule shedule, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(shedule);
            model.addAttribute("clazzes", clazzService.getAllClazzes());
            model.addAttribute("periods", sheduleService.getAllPeriods());
            model.addAttribute("subjects", sheduleService.getAllSubjects());
            model.addAttribute("teachers", sheduleService.getAllTeachers());
            return "/shedules/createOrUpdateSheduleForm";
        }

        sheduleService.saveShedule(shedule);

        return "redirect:/shedules/";
    }

    //READ CLASS

    @RequestMapping(value = "/shedules/{sheduleId}")
    public ModelAndView read() {

        ModelAndView mav = new ModelAndView("/shedules/sheduleDetails");

        return mav;
    }

    //UPDATE CLASS

    @RequestMapping(value = "/shedules/{sheduleId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("sheduleId") String sheduleIdStr) {

        ModelAndView mav = new ModelAndView("/shedules/createOrUpdateSheduleForm");

        return mav;
    }

    @RequestMapping(value = "/shedules/{sheduleId}/edit", method = RequestMethod.PUT)
    public String edit(BindingResult result, Model model) {

        return "redirect:/shedules/";
    }

    //DELETE CLASS

    @RequestMapping(value = "/shedules/{sheduleId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("sheduleId") String sheduleIdStr) {

        return new ModelAndView("redirect:/shedules");

    }

    //</editor-fold>
}
