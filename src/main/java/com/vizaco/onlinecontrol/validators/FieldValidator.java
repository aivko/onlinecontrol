package com.vizaco.onlinecontrol.validators;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class FieldValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return String.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        String field = (String) obj;

        if (obj == null || !StringUtils.hasText(field)){
            errors.rejectValue("startDate", "startDate.required");
        }

    }

}
