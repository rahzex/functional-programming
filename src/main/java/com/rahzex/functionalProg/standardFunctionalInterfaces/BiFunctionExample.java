package com.rahzex.functionalProg.standardFunctionalInterfaces;

import com.rahzex.functionalProg.data.Student;
import com.rahzex.functionalProg.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BiFunctionExample {

    private static Map<String, String> loginPageLocs = new HashMap<>();

    static BiFunction<List<Student>,Predicate<Student>,Map<String, Double>> biFunction = (students, studentPredicate)->{

        Map<String,Double> studentGradeMap = new HashMap<>();
        students.forEach((student -> {
            if(studentPredicate.test(student)){
                studentGradeMap.put(student.getName(),student.getGpa());
            }
        }));

        return studentGradeMap;
    };

    static BiFunction<String,String,String> getLoginLocs = (sLocator,elementType) -> loginPageLocs.get(sLocator);

    public static void main(String[] args) {

        System.out.println(biFunction.apply(StudentDataBase.getAllStudents(),(s) -> s.getGpa() > 3));

        getLoginLocs.apply("locator","elementType");

    }
}
