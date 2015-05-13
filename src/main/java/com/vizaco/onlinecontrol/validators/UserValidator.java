package com.vizaco.onlinecontrol.validators;


import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return User.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        User user = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "user.middleName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "user.passwordConfirm.required");

        String email = user.getEmail();
        if (StringUtils.hasText(email)) {
            User findUser = userService.findUserByEmail(email);
            if (findUser != null) {
                errors.rejectValue("email", "user.email.exist");
            }
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            errors.rejectValue("email", "user.email.matcher");
        }

        if ((user.getPassword()!=null && !user.getPassword().equals(user.getPasswordConfirm()))
                || (user.getPasswordConfirm()!=null && !user.getPasswordConfirm().equals(user.getPassword()))){
            errors.rejectValue("passwordConfirm", "user.passwordConfirm.passwordDiff");
            errors.rejectValue("password", "user.password.passwordDiff");
        }

    }

    public void validateEdit(User user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "user.middleName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");

    }


}
