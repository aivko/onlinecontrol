package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.Student;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.utils.Utils;
import com.vizaco.onlinecontrol.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController extends BaseController{

    private final RoleService roleService;

    @Autowired
    private Utils utils;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/roles")
    public ModelAndView initAccountForm() {

        ModelAndView mav = new ModelAndView("roles/roles");

        List<Role> roles = roleService.getAllRoles();

        mav.addObject("roles", roles);

        return mav;
    }

    //CREATE ROLE

    @RequestMapping(value = "/roles/new", method = RequestMethod.GET)
    public String register(Model model) {
        Role role = new Role();
        model.addAttribute(role);
        return "/roles/createOrUpdateRoleForm";
    }

    @RequestMapping(value = "/roles/new", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("role") @Validated Role role, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute(role);
            return "/roles/createOrUpdateRoleForm";
        }

        roleService.saveRole(role);
        return "redirect:/roles/";
    }

    //READ ROLE

    @RequestMapping(value = "/roles/{roleId}")
    public ModelAndView initAccountForm(@PathVariable("roleId") String roleIdStr) {

        Role role = utils.getRole(roleIdStr, null);

        ModelAndView mav = new ModelAndView("/roles/roleDetails");

        mav.addObject("role", role);

        return mav;
    }

    //UPDATE ROLE

    @RequestMapping(value = "/roles/{roleId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("roleId") String roleIdStr) {

        Role role = utils.getRole(roleIdStr, null);
        ModelAndView mav = new ModelAndView("/roles/createOrUpdateRoleForm");

        mav.addObject("role", role);

        return mav;
    }

    @RequestMapping(value = "/roles/{roleId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("roleId") String roleIdStr, @ModelAttribute("role") @Valid Role role, BindingResult result, Model model) {

        Role roleEdit = utils.getRole(roleIdStr, null);

        if(result.hasErrors()){
            model.addAttribute("role", role);
            return "/roles/createOrUpdateRoleForm";
        }

        role.setId(roleEdit.getId());
        roleService.saveRole(role);
        return "redirect:/roles/";
    }

    //DELETE ROLE

    @RequestMapping(value = "/roles/{roleId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("roleId") String roleIdStr) {

        Role role = utils.getRole(roleIdStr, null);
        roleService.deleteRole(role.getId());

        return new ModelAndView("redirect:/roles");

    }

}
