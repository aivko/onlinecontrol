package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.DateUtils;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.Utils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

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
    @Autowired
    private Utils utils;

    @RequestMapping(value = "/shedules")
    public ModelAndView shedules() {

        ModelAndView mav = new ModelAndView("shedules/shedules");

//        List<Shedule> shedules = sheduleService.getAllShedule();

        TreeSet<Shedule> shedules = new TreeSet<>(sheduleService.getAllShedule());

        mav.addObject("shedules", shedules);

        return mav;
    }

    @RequestMapping(value = "/shedules/viewShedule", method = RequestMethod.GET)
    public ModelAndView viewShedule() {

        ModelAndView mav = new ModelAndView("/shedules/viewShedule");
        return mav;

    }

    @RequestMapping(value = "/shedules/generateReport")
    @ResponseBody
    public String generateHtmlReport(@RequestBody String json){

        Resource resource = new DefaultResourceLoader().getResource("reports/hello.xml");

        try {
            InputStream inputStream = resource.getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JREmptyDataSource());

            JasperExportManager.exportReportToHtmlFile(jasperPrint, "/home/super/IdeaProjects/onlinecontrol/src/main/resources/reports/hello_report.html");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{\"result\":\"true\"}";

    }//generatePdfReport

    @RequestMapping(value = "/shedules/newTemplate", method = RequestMethod.GET)
    public String registerTemplate(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", sheduleService.getAllPeriods());
        model.addAttribute("subjects", sheduleService.getAllSubjects());
        model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
        model.addAttribute("teachers", sheduleService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/fillSheduleInTheTemplate";
    }

    @RequestMapping(value = "/shedules/newTemplate", method = RequestMethod.POST)
    public String registerTemplate(@RequestBody String json) {

        if (json == null) {
            return "/shedules/fillSheduleInTheTemplate";
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

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        Integer currentWeek;
        DayOfWeek currentDay;
        Period currentPeriod;
        Subject currentSubject;
        Teacher currentTeacher;

        for (String param : params) {

            if (param.startsWith("dayOfTheWeek")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentDay = DayOfWeek.of(Integer.parseInt(keyValue[1]))) == null){
                    continue;
                }
                daysOfTheWeek.put(keyValue[0], currentDay);
            } else if (param.startsWith("week_")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || ((currentWeek = Integer.parseInt(keyValue[1]))== null)) {
                    continue;
                }
                numberOfWeek.add(currentWeek);
            } else if (param.startsWith("period")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentPeriod = sheduleService.findPeriodById(Long.parseLong(keyValue[1]))) == null){
                    continue;
                }
                periods.put(keyValue[0], currentPeriod);
            } else if (param.startsWith("subject")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentSubject = sheduleService.findSubjectById(Long.parseLong(keyValue[1]))) == null){
                    continue;
                }
                subjects.put(keyValue[0], currentSubject);
            } else if (param.startsWith("teacher")) {
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue, 1) || (currentTeacher = sheduleService.findTeacherById(Long.parseLong(keyValue[1]))) == null){
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
            return "/shedules/fillSheduleInTheTemplate";
        } else if (startDate.compareTo(endDate) > 0) {
            return "/shedules/fillSheduleInTheTemplate";
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
                if (sheduleDB.getDate().getTime() == shedule.getDate().getTime() & sheduleDB.getPeriod().equals(shedule.getPeriod())){
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

        if(result.hasErrors()){
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
