package com.espire.practice;

public interface InterfaceB {
    default void test() {
        System.out.println("hello");
    }
}
