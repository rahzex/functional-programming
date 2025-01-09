package com.rahzex.functionalProg.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class Citizen {
    private transient String name;
    private Student student;
    private Date date;
    private List<String> addresses;

    public Citizen(String name, Student student, Date date, List<String> addresses) {
        this.name = name;
        this.student = new Student(student.getName(), student.getGradeLevel(), student.getGpa(), student.getGender(), new ArrayList<>());
        this.date = date;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return new Student(student.getName(), student.getGradeLevel(), student.getGpa(), student.getGender(), new ArrayList<>());
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public List<String> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }
}
