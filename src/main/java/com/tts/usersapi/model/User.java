package com.tts.usersapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max=20)
    private String firstName;

    @Length(min=2)
    private String lastName;

    @Size(min=4, max=20)
    private String state;

    public User(Long id, String firstName, String lastName, String state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", state=" + state + "]";
    }

    
}
