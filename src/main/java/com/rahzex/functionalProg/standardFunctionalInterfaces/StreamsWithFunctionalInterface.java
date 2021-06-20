package com.rahzex.functionalProg.standardFunctionalInterfaces;

import com.rahzex.functionalProg.data.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class StreamsWithFunctionalInterface {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 52),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 49)
        );
        operate(people,(firstName, lastName) -> {
            String res = firstName.concat("---").concat(lastName);
            System.out.println(res);
           return res;
        });
    }

    // BiFunction<T,U,R> : Functional Interface which represents a function that accepts two arguments and produces or returns
    // a result.
    private static void operate(List<Person> persons, BiFunction<String, String, String> biFunction){
        // Using Parallel Streams : makes operations faster as that part of code executes in parallel
        // here the age comparison and then operation on each element using forEach is done in parallel.
        // .filter : by default takes a Functional Interface Predicate<T> as parameter thus accepts an input and
        // returns a boolean
        // .forEach : by default takes a Functional Interface Consumer<T> as parameter thus accepts an input and
        //  returns nothing.
        persons.parallelStream().filter(person -> person.getAge() > 45)
                .forEach(person -> biFunction.apply(person.getFirstName(),person.getLastName()));

    }
}
