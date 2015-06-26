package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.*;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.JsonUtil;
import com.vizaco.onlinecontrol.utils.Utils;
import jdk.nashorn.internal.parser.DateParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class SheduleController extends BaseController{

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private UserService userService;

    JsonUtil jsonUtil = new JsonUtil();

    private Utils utils = new Utils();

    //<editor-fold desc="CRUD SHEDULE">
    //CREATE SHEDULE

    @RequestMapping(value = "/shedules/new", method = RequestMethod.GET)
    public String register(Model model) {

        model.addAttribute("clazzes", clazzService.getAllClazzes());
        model.addAttribute("periods", sheduleService.getAllPeriods());
        model.addAttribute("subjects", sheduleService.getAllSubjects());
        model.addAttribute("daysOfTheWeek", sheduleService.getAllDaysOfTheWeek());
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
                daysOfTheWeek.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("period")){
                String[] keyValue = param.split("=");
                periods.put(keyValue[0], keyValue[1]);
            } else if(param.startsWith("subject")){
                String[] keyValue = param.split("=");
                subjects.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("teacher")){
                String[] keyValue = param.split("=");
                teachers.put(keyValue[0], keyValue[1]);
            }else if(param.startsWith("startDate")){
                String[] keyValue = param.split("=");
                startDate = new GregorianCalendar();
                try {
                    startDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            }else if(param.startsWith("endDate")){
                String[] keyValue = param.split("=");
                endDate = new GregorianCalendar();
                try {
                    endDate.setTime(formatter.parse(keyValue[1]));
                } catch (ParseException e) {
                    break;
                }
            }else if(param.startsWith("classSelect")){
                String[] keyValue = param.split("=");
                if (keyValue[1] == null || keyValue[1] == "0"){
                    break;
                }
                clazz = clazzService.findClazzById(Long.parseLong(keyValue[1]));
            }

        }

        if (startDate == null || endDate == null || clazz == null){
            return "/shedules/createOrUpdateSheduleForm";
        }

        String currentNumber;

        DayOfTheWeek currentDay;
        Period currentPeriod;
        Subject currentSubject;
        Teacher currentTeacher;

        for (Map.Entry<String, String> day : daysOfTheWeek.entrySet()){

            String dayKey = day.getKey();
            String dayValue = day.getValue();

            currentNumber = dayKey.substring(12);

            currentDay = sheduleService.findDayOfTheWeekById(Long.parseLong(dayValue));

            String periodValue = periods.get("period" + currentNumber);
            currentPeriod = sheduleService.findPeriodById(Long.parseLong(periodValue));

            String subjectValue = subjects.get("subject" + currentNumber);
            currentSubject = sheduleService.findSubjectById(Long.parseLong(subjectValue));

            String teacherValue = teachers.get("teacher" + currentNumber);
            currentTeacher = sheduleService.findTeacherById(Long.parseLong(teacherValue));

        }

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
