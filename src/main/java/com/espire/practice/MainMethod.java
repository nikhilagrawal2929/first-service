package com.espire.practice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MainMethod {

    public static void main(String[] args) throws Exception {
        Employee1 employee1
                = new Employee1();
        employee1.setEmpAddress("Indore");
        employee1.setEmpName("Nikhil");
        System.out.println(employee1.getEmpId());
        Callable<String> stringCallable = employee1::check;
        stringCallable.call();

     FuntionalInterfaceTest funtionalInterfaceTest = employee1::getEmpName;
        System.out.println(funtionalInterfaceTest.test());
        List<String> list = Arrays.asList("a", "b", "c");

        list.forEach(System.out::println);
        //list.for
        Consumer<? super String> consumer = employee1::check;
        list.forEach(employee1::check1);

    }
}
