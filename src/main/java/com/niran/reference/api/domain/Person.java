package com.niran.reference.api.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private int age;


    public Person() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }


    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = firstname;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Adds and manipulates data of a model property.
    @ApiModelProperty(notes = "The first name of a person", required = true)
    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @ApiModelProperty(notes = "The last name of a person", required = true)
    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @ApiModelProperty(notes = "The age of a person", required = true)
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

}
