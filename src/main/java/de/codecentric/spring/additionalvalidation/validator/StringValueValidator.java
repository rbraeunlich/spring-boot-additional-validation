package de.codecentric.spring.additionalvalidation.validator;

import de.codecentric.spring.additionalvalidation.controller.Data;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StringValueValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Data.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (((Data) o).getSomeStringValue().length() > 2) {
            errors.reject("someStringValue");
        }
    }
}
