package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

@Controller
public class SheduleController extends BaseController{

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private UserService userService;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private Utils utils;


    //<editor-fold desc="CRUD SHEDULE">
    //CREATE SHEDULE

    @RequestMapping(value = "/shedules/new", method = RequestMethod.GET)
    public String register(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", sheduleService.getAllPeriods());
        model.addAttribute("subjects", sheduleService.getAllSubjects());
        model.addAttribute("daysOfWeek", dateUtils.getAllDaysOfTheWeek());
        model.addAttribute("teachers", sheduleService.getAllTeachers());
        model.addAttribute("shedule", new Shedule());

        return "/shedules/createOrUpdateSheduleForm";
    }

    @RequestMapping(value = "/shedules/new", method = RequestMethod.POST)
    public String save(@RequestBody String json) {

        if (json == null){
            return "/shedules/createOrUpdateSheduleForm";
        }

        String[] params = json.split("&");

        TreeMap<String, String> daysOfTheWeek = new TreeMap<>();
        TreeMap<String, String> periods = new TreeMap<>();
        TreeMap<String, String> subjects = new TreeMap<>();
        TreeMap<String, String> teachers = new TreeMap<>();

        GregorianCalendar startDate = null;
        GregorianCalendar endDate = null;

        Clazz clazz = null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        for (String param : params){

            if (param.startsWith("dayOfTheWeek")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                daysOfTheWeek.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("period")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                periods.put(keyValue[0], keyValue[1]);
            } else if(param.startsWith("subject")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                subjects.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("teacher")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                teachers.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("startDate")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                startDate = new GregorianCalendar();
                try {
                    startDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            }else if(param.startsWith("endDate")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                endDate = new GregorianCalendar();
                try {
                    endDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            }else if(param.startsWith("classSelect")){
                String[] keyValue = param.split("=");
                if (emptyValue(keyValue[1])) continue;
                clazz = clazzService.findClazzById(Long.parseLong(keyValue[1]));
            }

        }

        if (startDate == null || endDate == null || clazz == null){
            return "/shedules/createOrUpdateSheduleForm";
        } else if (startDate.compareTo(endDate) > 0){
            return "/shedules/createOrUpdateSheduleForm";
        }

        TreeMap<GregorianCalendar, DayOfWeek> calendarDayOfTheWeekMap = new TreeMap<>();

        while (startDate.compareTo(endDate) < 0){

            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.setTime(startDate.getTime());

            calendarDayOfTheWeekMap.put(currentDate, DayOfWeek.of(dateUtils.getNumberDayOfWeek(startDate)));
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        String currentNumber;

        DayOfWeek currentDay;
        Period currentPeriod;
        Subject currentSubject;
        Teacher currentTeacher;

        for (Map.Entry<String, String> day : daysOfTheWeek.entrySet()){

            String dayKey = day.getKey();
            String dayValue = day.getValue();

            currentNumber = dayKey.substring(12);

            currentDay = DayOfWeek.of(Integer.parseInt(dayValue));

            String periodValue = periods.get("period" + currentNumber);
            currentPeriod = sheduleService.findPeriodById(Long.parseLong(periodValue));

            String subjectValue = subjects.get("subject" + currentNumber);
            currentSubject = sheduleService.findSubjectById(Long.parseLong(subjectValue));

            String teacherValue = teachers.get("teacher" + currentNumber);
            currentTeacher = sheduleService.findTeacherById(Long.parseLong(teacherValue));

        }

        return "redirect:/shedules/";
    }

    private boolean emptyValue(String value) {
        if (value == "0" || value == null) return true;
        return false;
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
