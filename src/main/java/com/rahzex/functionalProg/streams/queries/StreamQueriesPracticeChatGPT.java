package com.rahzex.functionalProg.streams.queries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.rahzex.functionalProg.data.Employee;
import com.rahzex.functionalProg.data.EmployeeDatabase;
import com.rahzex.functionalProg.data.Product;

public class StreamQueriesPracticeChatGPT {

    public static void main(String[] args) {
        /**
         *
         Problem 1: Frequency of Words by Length
         Given a list of strings, count the frequency of words based on their length.
         Return a `Map<Integer, Long>` where the key is the length of the word,
         and the value is the count of words of that length.

         For example:
         Input: ["apple", "banana", "cherry", "date", "fig", "grape", "kiwi"]
         Output: {5=3, 6=3, 4=1}
         */
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape", "kiwi");
        Map<Integer, Long> similarLengthWordCount = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        similarLengthWordCount.forEach((wordLength, count) -> System.out.println(wordLength + "=" + count));

        /**
         * ### Problem 2: Group Employees by Department and Calculate Average Age
         * You are given a list of employees where each employee has a name, age, and department.
         * Write a program to group the employees by their department and calculate the average age of employees in each department.
         * Return a `Map<String, Double>` where the key is the department name, and the value is the average age of employees in that department.
         * Input:
         * List<Employee> employees = Arrays.asList(
         *     new Employee("Alice", 30, "HR"),
         *     new Employee("Bob", 40, "Finance"),
         *     new Employee("Charlie", 35, "HR"),
         *     new Employee("David", 50, "Finance"),
         *     new Employee("Eve", 25, "IT")
         * );
         *
         * Output:
         * {HR=32.5, Finance=45.0, IT=25.0}
         */
        List<Employee> employees = EmployeeDatabase.getAllEmployees();
        employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::age)))
                .forEach((department, avgAge) -> System.out.println(department + "=" + avgAge));
        /**
         * ### Problem 3: Partition Numbers into Even and Odd, Then Find Their Sum.
         * You are given a list of integers. Partition the numbers into two groups: even and odd.
         * Then, calculate the sum of numbers in each group. Return a `Map<Boolean, Integer>`
         * where the key `true` represents even numbers and `false` represents odd numbers,
         * and the value is the sum of the numbers in that group.
         *
         * #### Example:
         * Input: List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
         * Output: {false=25, true=30}
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0, Collectors.summingInt(number -> number)))
                .forEach((oddOrEven, sum) -> System.out.println(oddOrEven + "=" + sum));

        Map<Boolean, Integer> result = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 2 == 0, Collectors.summingInt(number -> number)));
        result.forEach((oddOrEven, sum) -> System.out.println(oddOrEven + "=" + sum));
        /**
         * ### Problem 4: Group Products by Category and Find the Most Expensive Product in Each Category
         *
         * You are given a list of `Product` objects. Each `Product` has a name, price, and category.
         * Group the products by their category and find the most expensive product in each category.
         * Return a `Map<String, Product>` where the key is the category name and the value is the most expensive product in that category.
         *
         * #### Example:
         * Input:
         * List<Product> products = Arrays.asList(
         *     new Product("Laptop", 1500.0, "Electronics"),
         *     new Product("Phone", 800.0, "Electronics"),
         *     new Product("TV", 1200.0, "Electronics"),
         *     new Product("Sofa", 500.0, "Furniture"),
         *     new Product("Table", 300.0, "Furniture"),
         *     new Product("Chair", 150.0, "Furniture")
         * );
         *
         * Output:
         * {Electronics=Product{name='Laptop', price=1500.0, category='Electronics'},
         *  Furniture=Product{name='Sofa', price=500.0, category='Furniture'}}
         */

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1500.0, "Electronics"));
        products.add(new Product("Phone", 800.0, "Electronics"));
        products.add(new Product("TV", 1200.0, "Electronics"));
        products.add(new Product("Sofa", 500.0, "Furniture"));
        products.add(new Product("Table", 300.0, "Furniture"));
        products.add(new Product("Chair", 150.0, "Furniture"));

        products.stream().collect(Collectors.groupingBy(Product::category, Collectors.maxBy(Comparator.comparing(Product::price))))
                .forEach((category, productOpt) -> productOpt.ifPresent(product -> System.out.println(category + "=" + product)));
        /**
         * ### Problem 5: Find the Top 3 Salaries in Each Department
         *
         * You are given a list of `Employee` objects. Each `Employee` has a name, salary, and department.
         * Group the employees by their department and find the top 3 salaries in each department.
         * Return a `Map<String, List<Employee>>` where the key is the department name,
         * and the value is a list of the top 3 employees with the highest salaries in that department.
         *
         * #### Example:
         * Output:
         * {HR=[Alice, Charlie, Bob],
         *  Finance=[David, Eve, Frank],
         *  IT=[Ivy, Hank]}
         */

        Map<String, List<Employee>> top3SalariesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Employee::salary).reversed())
                                        .limit(3)
                                        .toList()
                        )
                ));

        top3SalariesByDepartment.forEach((department, topEmployees) ->
                System.out.println(department + "=" + topEmployees));

        /**
         * ### Problem 6: Find the Department with the Highest Total Salary
         *
         * You are given a list of `Employee` objects. Each `Employee` has a name, salary, and department.
         * Find the department with the highest total salary.
         * Return the department name and its total salary as a `Map.Entry<String, Double>`.
         *
         * #### Example
         * Output:
         * Finance=410000.0
         * ```
         *
         * #### Try solving this and share your solution!
         */

        employees.stream().collect(Collectors.groupingBy(Employee::department,
                Collectors.summingDouble(Employee::salary)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);
        /**
         * ### Problem 7: Find the Longest Word Grouped by Starting Letter
         *
         * You are given a list of words.
         * Group the words by their starting letter and find the longest word in each group.
         * Return a `Map<Character, String>` where the key is the starting letter,
         * and the value is the longest word for that letter.
         *
         * #### Example:
         * Input:
         * List<String> words = Arrays.asList("apple", "ape", "bat", "banana", "cat", "carrot", "dog", "dove");
         *
         * Output:
         * {a=apple, b=banana, c=carrot, d=dove}
         */
        List<String> wds = Arrays.asList("apple", "ape", "bat", "banana", "cat", "carrot", "dog", "dove");

        wds.stream().collect(Collectors.groupingBy(word -> word.charAt(0),
                Collectors.maxBy(Comparator.comparing(String::length))))
                .forEach((firstChar, wordOp) -> wordOp
                        .ifPresent(word -> System.out.println(firstChar + "=" + word)));
        /**
         * ### Problem 8: Partition Strings Based on Length and Count Vowels in Each Group
         *
         * You are given a list of words. Partition the words into two groups:
         * 1. Words with a length greater than 5.
         * 2. Words with a length less than or equal to 5.
         *
         * For each group, calculate the total count of vowels (a, e, i, o, u) in all the words of that group.
         * Return a `Map<Boolean, Integer>` where:
         * - `true` represents words with a length greater than 5.
         * - `false` represents words with a length less than or equal to 5.
         *
         * #### Example:
         * Input:
         * List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "kiwi", "grape");
         *
         * Output:
         * {false=8, true=10}
         */
        wds.stream().collect(Collectors.partitioningBy(word -> word.length() > 5,
                Collectors.summingInt(word -> Math.toIntExact(word.chars()
                        .filter(ch -> "aeiou".indexOf(ch) >= 0)
                        .count())
                )))
                .forEach((aBoolean, vowelCount) -> System.out.println(aBoolean + "=" + vowelCount));

        /**
         * ### Problem 9: Find the Most Frequent Character in Each Word
         *
         * You are given a list of words. For each word,
         * find the character that occurs the most frequently.
         * If there is a tie, return the lexicographically smallest character.
         * Return a `Map<String, Character>` where the key is the word,
         * and the value is the most frequent character in that word.
         *
         * #### Example:
         * Input:
         * List<String> words = Arrays.asList("apple", "banana", "cherry");
         *
         * Output:
         * {apple=p, banana=a, cherry=r}
         *
         * #### Constraints:
         * - Consider only alphabetical characters (`a-z` and `A-Z`).
         * - Treat characters case-insensitively, but return the result in lowercase.
         */
        List<String> wds1 = Arrays.asList("apple", "banana", "cherry");
        wds1.stream().collect(Collectors.toMap(word -> word,
                word -> Arrays.stream(word.split(""))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
        )).forEach((word, character) -> character.ifPresent(ch -> System.out.println(word + "=" + ch)));


        /**
         * ### Problem 11: Create a Comma-Separated String of Employee Names by Department
         *
         * You are given a list of `Employee` objects. Each `Employee` has a name and a department.
         * Group the employees by their department and create a comma-separated string of employee names for each department.
         * Return a `Map<String, String>` where:
         * - Key is the department.
         * - Value is a comma-separated string of employee names in that department.
         *
         * #### Example:
         * Input:
         * List<Employee> employees = Arrays.asList(
         *     new Employee("Alice", "HR"),
         *     new Employee("Bob", "HR"),
         *     new Employee("Charlie", "Finance"),
         *     new Employee("David", "Finance"),
         *     new Employee("Eve", "IT")
         * );
         *
         * Output:
         * {
         *     HR="Alice, Bob",
         *     Finance="Charlie, David",
         *     IT="Eve"
         * }
         */
        employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.mapping(
                        Employee::name,
                        Collectors.joining(", ")
                )))
                .forEach((department, employeeNames) -> System.out.println(department + " = " + employeeNames));

        /**
         * ### Problem 12: Find the Shortest and Longest Words Grouped by Their First Letter
         *
         * You are given a list of words.
         * Group the words by their first letter and, for each group find the shortest and longest word.
         * Return a `Map<Character, String>` where:
         * - Key is the first letter of the words.
         * - Value is a string in the format: `shortest_word -> longest_word`.
         *
         * #### Example:
         * ```java
         * Input:
         * List<String> words = Arrays.asList("apple", "apricot", "banana", "cherry", "date", "kiwi", "grape");
         *
         * Output:
         * {
         *     a="apple -> apricot",
         *     b="banana -> banana",
         *     c="cherry -> cherry",
         *     d="date -> date",
         *     g="grape -> grape",
         *     k="kiwi -> kiwi"
         * }
         * #### Hints:
         * 1. Use `Collectors.groupingBy` to group by the first letter of each word.
         * 2. Use `Collectors.collectingAndThen` with `Collectors.reducing` to find the shortest and longest words compactly.
         */
        List<String> wordss = Arrays.asList("apple", "apricot", "banana", "cherry", "date", "kiwi", "grape");
        wordss.stream()
                .collect(Collectors.groupingBy(
                        word -> word.charAt(0), // Group by the first letter
                        Collectors.collectingAndThen(
                                Collectors.reducing(
                                        new String[]{null, null}, // Array to store shortest and longest words
                                        word -> new String[]{word, word}, // Initialize with the current word
                                        (a, b) -> new String[]{ // Merge arrays to find shortest and longest
                                                a[0].length() <= b[0].length() ? a[0] : b[0], // Shortest
                                                a[1].length() >= b[1].length() ? a[1] : b[1]  // Longest
                                        }
                                ),
                                arr -> arr[0] + " -> " + arr[1] // Format as "shortest -> longest"
                        )
                )).forEach((letter, word) -> System.out.println(letter + "=" + word));

        /**
         * ### Problem 13: Count the Number of Distinct Words by Their Length
         *
         * You are given a list of words.
         * Group the words by their length and count how many distinct words exist for each length.
         * Return a `Map<Integer, Long>` where:
         * - Key is the word length.
         * - Value is the count of distinct words of that length.
         *
         * #### Example:
         * Input:
         * List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape", "kiwi", "date", "fig");
         *
         * Output:
         * {
         *     3=2,
         *     4=1,
         *     5=1,
         *     6=3
         * }
         */
        wordss.stream().collect(Collectors.groupingBy(String::length,
                        Collectors.collectingAndThen(
                                Collectors.toSet(),
                                Set::size)))
                .forEach((length, distinctWordCount) -> System.out.println(length + "=" + distinctWordCount));
    }
}
