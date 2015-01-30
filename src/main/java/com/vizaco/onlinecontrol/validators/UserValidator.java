package com.vizaco.onlinecontrol.validators;


import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.service.StudentService;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "user.login.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");

        String login = user.getLogin();
        if (StringUtils.hasText(login)){
            User findUser = userService.findUserByLogin(login);
            if (findUser != null){
                errors.rejectValue("login", "user.login.exist");
            }
        }

    }
    
}
