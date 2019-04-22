package org.senolab.springbootappenginedatastoredemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    private String id;

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    @NotNull
    @Min(1)
    private int age;

    public User() {
    }

    public User(String id, String email, String password, String fullname, Integer age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
}
