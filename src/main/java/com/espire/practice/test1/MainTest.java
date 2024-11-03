package com.espire.practice.test1;

import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        ImmutableWithMutableCollection immutableWithMutableCollection
                = new ImmutableWithMutableCollection(1, Arrays.asList("1","2"));
        List<String> list =immutableWithMutableCollection.getMutableList();
        list.add("3");
        System.out.println(immutableWithMutableCollection.getMutableList());
        System.out.println(immutableWithMutableCollection);
    }
}
