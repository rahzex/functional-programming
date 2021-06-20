package com.rahzex.functionalProg.basicsOfFunctionalProg;
import java.util.Arrays;
import java.util.List;

public class DeclarativeProg {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Nylon");

        //find a nemo ; declarative
        names.stream().filter(name -> name.equals("Nemo")).forEach(res -> System.out.println(res));
        names.stream().filter(name -> name.contains("i")).forEach(System.out::println);
        names.stream().sorted(String::compareTo).forEach(System.out::println);
    }
}
