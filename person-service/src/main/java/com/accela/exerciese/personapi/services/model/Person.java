package com.accela.exerciese.personapi.services.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.*;

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
