package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender implements IBaseEnum{
    MALE("0"),
    FEMALE("1");

    @JsonValue
    private String value;
    private Gender(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
