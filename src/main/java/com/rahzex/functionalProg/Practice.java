package com.rahzex.functionalProg;

import com.rahzex.functionalProg.data.Citizen;
import com.rahzex.functionalProg.data.Employee;
import com.rahzex.functionalProg.data.EmployeeDatabase;
import com.rahzex.functionalProg.data.Student;
import com.rahzex.functionalProg.data.StudentDataBase;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

//        Map<String, Double> studentMap = StudentDataBase.getAllStudents()
//                .stream()
//                .filter(student -> student.getGradeLevel() >= 3 && student.getGpa() >= 3.9)
//                .collect(Collectors.toMap(Student::getName, Student::getGpa));
//
        StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGradeLevel() >= 1 && student.getGpa() >= 2)
                .sorted(Comparator.comparing(Student::getName).reversed().thenComparing(Student::getGradeLevel))
                .forEach(System.out::println);
//
//        Collections.sort(StudentDataBase.getAllStudents(), Comparator.comparing(Student::getName));
//
//        studentMap.keySet()
//                .forEach(studentMap::get);
//
//        List<Integer> nums = Arrays.asList(1,3,6,8,3,7);
//
//        nums.stream()
//                .reduce(1, (n1, n2) -> n1 * n2 );
//
        var employees = EmployeeDatabase.getAllEmployees();
//
//        employees.stream().sorted(Comparator.comparing(Employee::department))
//                .forEach(System.out::println);

//        List<Integer> values = List.of(1,2,3,4,5,6,7,8,9);
//         values.stream()
//                 .sorted()
//                .filter(val -> {
//                    System.out.println(val);
//                    return val > 5;
//                }).findFirst();


    }
}
