package com.accela.exerciese.personapi.services.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(exclude = "addressList")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private int id;


    private String firstname;

    private String lastname;

    private int age;

    private String emailId;

}
