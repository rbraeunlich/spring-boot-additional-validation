package de.codecentric.spring.additionalvalidation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidationController {

    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public HttpStatus acceptData(@Valid @RequestBody Data data){
        return HttpStatus.OK;
    }
}
