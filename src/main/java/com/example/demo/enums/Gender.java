package com.example.demo.enums;

public enum Gender implements IBaseEnum{
    MALE('0'),
    FEMALE('1');

    private Character value;
    private Gender(Character value){
        this.value = value;
    }

    @Override
    public Character getValue() {
        return this.value;
    }
}
