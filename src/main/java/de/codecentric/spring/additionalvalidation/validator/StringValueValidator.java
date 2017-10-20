package de.codecentric.spring.additionalvalidation.validator;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.codecentric.spring.additionalvalidation.controller.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;

@Component
public class StringValueValidator {

    public void validate(String language, Data data, Errors errors) {
        if (!"de-DE".equals(language)) {
            if (data.getSomeStringValue().length() > 140) {
                errors.reject("someStringValue");
            }
        }
    }
}
