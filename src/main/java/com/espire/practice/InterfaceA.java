package com.espire.practice;

public interface InterfaceA {
    default void test() {
        System.out.println("hello");
    }
}
