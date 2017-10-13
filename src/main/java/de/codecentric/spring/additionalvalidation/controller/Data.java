package de.codecentric.spring.additionalvalidation.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Data {

    @NotNull
    private final String someStringValue;

    @Min(1)
    private final int someIntValue;

    @JsonCreator
    public Data(@JsonProperty("someStringValue") String someStringValue, @JsonProperty("someIntValue") int someIntValue) {
        this.someStringValue = someStringValue;
        this.someIntValue = someIntValue;
    }

    public String getSomeStringValue() {
        return someStringValue;
    }

    public int getSomeIntValue() {
        return someIntValue;
    }
}
