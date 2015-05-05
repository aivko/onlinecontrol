package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Clazz;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.ClazzService;
import com.vizaco.onlinecontrol.service.RoleService;
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
public class ClazzController {

    private final ClazzService clazzService;

    private Utils utils = new Utils();

    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @RequestMapping(value = "/classes")
    public ModelAndView initAccountForm() {

        ModelAndView mav = new ModelAndView("classes/classes");

        List<Clazz> clazzes = clazzService.getAllClazzes();

        mav.addObject("clazzes", clazzes);

        return mav;
    }

    //CREATE CLASS

    @RequestMapping(value = "/classes/new", method = RequestMethod.GET)
    public String register(Model model) {
        Clazz clazz = new Clazz();
        model.addAttribute(clazz);
        return "/classes/createOrUpdateClassForm";
    }

    @RequestMapping(value = "/classes/new", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("clazz") @Validated Clazz clazz, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute(clazz);
            return "/classes/createOrUpdateClassForm";
        }

        clazzService.saveClazz(clazz);
        return "redirect:/classes/";
    }

    //READ CLASS

    @RequestMapping(value = "/classes/{clazzId}")
    public ModelAndView initAccountForm(@PathVariable("clazzId") String clazzIdStr) {

        Clazz clazz = utils.getClazz(clazzIdStr, clazzService);

        ModelAndView mav = new ModelAndView("/classes/classDetails");

        mav.addObject("clazz", clazz);

        return mav;
    }

    //UPDATE CLASS

    @RequestMapping(value = "/classes/{clazzId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("clazzId") String clazzIdStr) {

        Clazz clazz = utils.getClazz(clazzIdStr, clazzService);
        ModelAndView mav = new ModelAndView("/classes/createOrUpdateClassForm");

        mav.addObject("clazz", clazz);

        return mav;
    }

    @RequestMapping(value = "/classes/{clazzId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("clazzId") String clazzIdStr, @ModelAttribute("clazz") @Valid Clazz clazz, BindingResult result, Model model) {

        Clazz clazzEdit = utils.getClazz(clazzIdStr, clazzService);

        if(result.hasErrors()){
            model.addAttribute("clazz", clazz);
            return "/classes/createOrUpdateClassForm";
        }

        clazz.setClazzId(clazzEdit.getClazzId());
        clazzService.saveClazz(clazz);
        return "redirect:/classes/";
    }

    //DELETE CLASS

    @RequestMapping(value = "/classes/{clazzId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("clazzId") String clazzIdStr) {

        Clazz clazz = utils.getClazz(clazzIdStr, clazzService);
        clazzService.deleteClazz(clazz.getClazzId());

        return new ModelAndView("redirect:/classes");

    }

}
