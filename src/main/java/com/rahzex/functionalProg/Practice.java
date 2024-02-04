package com.rahzex.functionalProg;

import com.rahzex.functionalProg.data.Student;
import com.rahzex.functionalProg.data.StudentDataBase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

        Map<String, Double> studentMap = StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGradeLevel() >= 3 && student.getGpa() >= 3.9)
                .collect(Collectors.toMap(Student::getName, Student::getGpa));

        StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGradeLevel() >= 1 && student.getGpa() >= 2)
                .sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);

        //System.out.println(studentMap);
        Collections.sort(StudentDataBase.getAllStudents(),
                Comparator.comparing(Student::getName));

        studentMap.keySet()
                .forEach(studentMap::get);

        List<Integer> nums = Arrays.asList(1,3,6,8,3,7);

        nums.stream()
                .reduce(1, (n1, n2) -> n1 * n2 );
    }
}
