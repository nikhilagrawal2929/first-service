package com.espire.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Alex", "Charlie", "Annie", "David");
//Filter the names that start with the letter "A".
        List startWithAList = names.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());

        System.out.println(startWithAList);
        List startWithAListAndUpper = names.stream().filter(s -> s.startsWith("A")).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(startWithAListAndUpper);
        List<String> strings = names.stream().sorted().collect(Collectors.toList());
        System.out.println(strings);

    }
}
