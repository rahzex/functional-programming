package com.rahzex.functionalProg.streams.queries;

import com.rahzex.functionalProg.data.Employee;
import com.rahzex.functionalProg.data.EmployeeDatabase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamQueriesExample {

    /**
     *  https://www.javabrahman.com/java-8/java-8-java-util-stream-collector-basics-tutorial-with-examples/
     *  https://javaconceptoftheday.com/solving-real-time-queries-using-java-8-features-employee-management-system/
     *  https://www.youtube.com/watch?v=1Ps5F1PU72M
     */
    public static void main(String[] args) {
        List<Employee> employees = EmployeeDatabase.getAllEmployees();

        // How many male and female employees are there in the organization?
        Map<String, Long> noOfMaleAndFemaleEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.counting()));
        System.out.println(noOfMaleAndFemaleEmployees);

        // What is the average age of male and female employees?
        Map<String, Double> avgAgeOfMaleAndFemaleEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.averagingInt(Employee::age)));

        // Get the details of highest paid employee in the organization?
        employees.stream().max(Comparator.comparing(Employee::salary))
                .ifPresent(System.out::println);

        // Get the details of youngest male employee in the product development department?
        employees.stream()
                .filter(employee -> "Product Development".equals(employee.department()) && "Male".equals(employee.gender()))
                .min(Comparator.comparingInt(Employee::age))
                .ifPresent(System.out::println);

        // What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics statistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

        // Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> partitionEmployeesByAge =
                employees.stream().collect(Collectors.partitioningBy(e -> e.age() > 25));


        // find characters whose occurrence is more than once from a given string
        String str = "HelloItsMeAgain";
        Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(key -> key.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        // find the first non-repeated character from a given string
        Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new , Collectors.counting()))
                .entrySet().stream()
                .filter(element -> element.getValue() == 1)
                .findFirst()
                .ifPresent(resultSet -> System.out.println(resultSet.getKey()));

        // find word with the highest length
        String[] strArr = {"hello","streams","hi","majority"};
        Arrays.stream(strArr)
                .reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
                .ifPresent(System.out::println);

        // Print all males employees separately who are above and below the age of 30
        Map<Boolean, List<Employee>> employeesPartitionedByAge = employees.stream()
                .filter(employee -> employee.gender().equalsIgnoreCase("male"))
                .collect(Collectors.partitioningBy(employee -> employee.age() > 30));
        employeesPartitionedByAge.forEach((aBoolean, employees1) -> System.out.println(aBoolean + " : " + employees1.toString()));

        // count all males employees separately who are above and below the age of 30
        Map<Boolean, Long> countOfEmployeesPartitionedByAge = employees.stream()
                .filter(employee -> employee.gender().equalsIgnoreCase("male"))
                .collect(Collectors.partitioningBy(employee -> employee.age() > 30, Collectors.counting()));
        countOfEmployeesPartitionedByAge.forEach((olderThanThirty, count) -> System.out.println(olderThanThirty + " : " + count));

        /* Print all employees name in following manner.
        Group the words by their first character and, for each group, concatenate the words in uppercase separated by a semicolon (;).
        Input Example: ("apple", "banana", "apricot", "blueberry", "avocado")
        Expected Output:
                a=APPLE;APRICOT;AVOCADO
                b=BANANA;BLUEBERRY
         */
        employees.stream()
                .map(Employee::name)
                .collect(Collectors.groupingBy(name -> name.charAt(0),
                        Collectors.mapping(String::toUpperCase, Collectors.joining(";"))))
                .forEach((character, s) -> System.out.println(character + "=" + s));

        // Given a String s = "aabbbcccc" , we have use java streams to convert this to another string op = "a2b3c4"
        String str1 = "aabbbccc";
        Arrays.stream(str1.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((s, aLong) -> System.out.print(s + aLong));
    }

}
