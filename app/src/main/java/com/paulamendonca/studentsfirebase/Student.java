package com.paulamendonca.studentsfirebase;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String course;
    private String email;

    public Student () {}

    public String getName () { return name; }
    public void setName (String name) { this.name = name; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @NonNull
    @Override
    public String toString() { return name + " " + course + " " + email; }

}
