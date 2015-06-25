package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.SheduleService;
import com.vizaco.onlinecontrol.service.UserService;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SheduleController extends BaseController{

    @Autowired
    private SheduleService sheduleService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private UserService userService;

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

        return "/shedules/createOrUpdateSheduleForm";
    }

    @RequestMapping(value = "/shedules/new", method = RequestMethod.POST)
    public String saveRole(BindingResult result, Model model) {

        return "redirect:/shedules/";
    }

    //READ CLASS

    @RequestMapping(value = "/shedules/{sheduleId}")
    public ModelAndView initAccountForm() {

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
