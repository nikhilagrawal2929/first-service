package com.espire.practice;

import java.util.function.Consumer;
import java.util.function.LongConsumer;

public class ChainingCosumer {
    public static void main(String[] args) {
        //List<String> names = Arrays.asList("Test1", "test2", "TESt3");
        Employee1 employee1 = new Employee1();
        employee1.setEmpAddress("Indore");
        Consumer<Employee1> consumer = t -> t.setCity("Indore");

        Consumer<Employee1> consumer2 = t -> t.setEmpId(101);
        Consumer<Employee1> combinedConsumer = consumer.andThen(consumer2);
    combinedConsumer.accept(employee1);
        System.out.println(employee1);

        LongConsumer longConsumer = t-> t=t+1;
        long size =12l;
        longConsumer.accept(size);
        System.out.println(size);

    }

}
