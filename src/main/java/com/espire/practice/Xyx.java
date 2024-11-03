package com.espire.practice;

import java.util.function.Function;

public class Xyx implements InterfaceB,InterfaceA {

    public static void main(String[] args) {
        Xyx xyx = new Xyx();
        //xyx.test();
    }

    @Override
    public void test() {
        //InterfaceB.super.test();
    }
}
