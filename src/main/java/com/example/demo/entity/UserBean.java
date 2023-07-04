package com.example.demo.entity;

import com.example.demo.annotation.GenderType;
import com.example.demo.enums.Gender;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.example.demo.enums.GenderConverter;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class UserBean {
    @Id
    @NotBlank(message = "姓名必须")
    @Column(name = "name")
    private String name = "";

    @Column(name = "gender")
    @GenderType(enumClass = Gender.class)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name="age")
    private int age;
}
