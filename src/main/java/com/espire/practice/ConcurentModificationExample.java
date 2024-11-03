package com.espire.practice;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ConcurentModificationExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

      numbers =  Collections.synchronizedList(numbers);
        List<Integer> numbersThreadsSafe = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbersThreadsSafe.add(i);
        }

        System.out.println( numbersThreadsSafe.stream().peek(System.out::println).collect(Collectors.toList()));
        System.out.println( numbers.stream().peek(System.out::println).collect(Collectors.toList()));

        List<Integer> finalNumbers = numbers;
        numbers.stream().peek(p-> finalNumbers.remove(p)).collect(Collectors.toList());
        System.out.println(numbersThreadsSafe);
        numbers.stream().peek(p->numbersThreadsSafe.remove(p)).collect(Collectors.toList());

    }
}
