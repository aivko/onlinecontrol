package com.vizaco.onlinecontrol.validators;


import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "user.passwordConfirm.required");

        validateEmail(errors, user);

        checkPassword(errors, user);

    }

    private void validateEmail(Errors errors, User user) {
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
    }

    public void checkPassword(Errors errors, User user) {
        if (checkPasswordEquals(user)) {
            errors.rejectValue("passwordConfirm", "user.passwordConfirm.passwordDiff");
            errors.rejectValue("password", "user.password.passwordDiff");
        }
    }

    public boolean checkPasswordEquals(User user) {
        return (user.getPassword()!=null && !user.getPassword().equals(user.getPasswordConfirm()))
                || (user.getPasswordConfirm()!=null && !user.getPasswordConfirm().equals(user.getPassword()));
    }

    public void validateEdit(User user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.required");

        validateEmail(errors, user);

    }


}
