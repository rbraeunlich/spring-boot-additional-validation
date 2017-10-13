package de.codecentric.spring.additionalvalidation.validator;

import de.codecentric.spring.additionalvalidation.controller.Data;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class StringValueValidator {

    public void validate(Data data, Errors errors) {
        if (data.getSomeStringValue().length() > 2) {
            errors.reject("someStringValue");
        }
    }
}
