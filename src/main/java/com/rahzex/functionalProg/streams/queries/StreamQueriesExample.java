package com.rahzex.functionalProg.streams.queries;

import com.rahzex.functionalProg.data.Employee;
import com.rahzex.functionalProg.data.EmployeeDatabase;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamQueriesExample {

    /**
     *  https://javaconceptoftheday.com/solving-real-time-queries-using-java-8-features-employee-management-system/
     */
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDatabase.getAllEmployees();

        // How many male and female employees are there in the organization?
        Map<String, Long> noOfMaleAndFemaleEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(noOfMaleAndFemaleEmployees);

        // What is the average age of male and female employees?
        Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

        // Get the details of highest paid employee in the organization?
        employees.stream().max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

        // Get the details of youngest male employee in the product development department?
        employees.stream()
                .filter(employee -> "Product Development".equals(employee.getDepartment()) && "Male".equals(employee.getGender()))
                .min(Comparator.comparingInt(Employee::getAge))
                .ifPresent(System.out::println);

        // What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics statistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

        // Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> partitionEmployeesByAge =
                employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));

    }

}
