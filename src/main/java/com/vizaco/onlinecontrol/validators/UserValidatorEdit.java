package com.vizaco.onlinecontrol.validators;


import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidatorEdit {

    public void validate(User user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "user.middleName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "user.login.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");

    }
    
}
