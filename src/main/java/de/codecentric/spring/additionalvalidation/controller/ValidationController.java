package de.codecentric.spring.additionalvalidation.controller;

import de.codecentric.spring.additionalvalidation.validator.StringValueValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class ValidationController {

    private final StringValueValidator stringValueValidator;

    @Autowired
    public ValidationController(StringValueValidator stringValueValidator) {
        this.stringValueValidator = stringValueValidator;
    }

    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public ResponseEntity<?> acceptData(@Validated @RequestBody Data data, Errors errors, @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {
        if (!"de-DE".equals(language)) {
            stringValueValidator.validate(data, errors);
        }
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String createErrorString(Errors errors) {
        return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
    }
}
