package com.bgroup.mis.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Student {
    @Id
    @GeneratedValue()
    private long id;
    @Column(name="fname",nullable = false,length = 50)
    private String firstName;
    @Column(name="lname",nullable = false,length = 50)
    private String lastName;
    @Column(name="email",nullable = false,length = 100)
    private String email;
    @Column(name="dob",nullable = false)
    private LocalDate dob;

    @Transient
    private int age;

    public Student() {
    }

    public Student(long id, String firstName, String lastName, String email, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }


    public Student(String firstName, String lastName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        if(this.dob != null){
            return Period.between(this.dob, today).getYears();
        }
        return 0;
    }

    public void setAge() {
        this.age = LocalDate.now().getYear() - this.dob.getYear();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", age=" + this.getAge() +
                '}';
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
