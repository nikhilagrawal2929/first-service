package com.espire.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
       /* List<String> names = List.of("Alice", "Bob", "Charlie");

        // Using Consumer to print each name
        Consumer<String> printName = name -> System.out.println(name);
        names.forEach(printName);  // Using forEach to apply the Consumer*/


            List<String> names = Arrays.asList("John", "Jane", "Jack");


            // Consumer to print each element in the list
            Consumer<String> printConsumer = name -> System.out.println(name+ "---");
            Consumer<String> printConsumer1 = System.out::println;

            // Using forEach to apply Consumer
            names.forEach(printConsumer);  // prints each name
            names.forEach(printConsumer1);  // prints each name
    }
}
