package com.example.demo.entity;

import com.example.demo.annotation.GenderType;
import com.example.demo.enums.Gender;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.example.demo.enums.GenderConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean implements Serializable {
    private static final long serialVersionUID = 7049909329275043187L;
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
