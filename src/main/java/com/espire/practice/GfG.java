package com.espire.practice;// Java program to find the duplicate
// elements in a Stream using Set 

import java.util.*; 
import java.util.stream.*; 

public class GfG 
{

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("grapes","apple", "banana", "apple", "orange"));
        HashSet hashSet = new HashSet(list);
        System.out.println("Elements" + hashSet);

        System.out.println(list.stream().distinct().collect(Collectors.toList()));

        //to remove the duplicate and maintian the insertion order then use linkedHashSet

        LinkedHashSet linkedHashSet = new LinkedHashSet(list);
        System.out.println(linkedHashSet);

        //System.out.println(list.stream().filter(s->list.contains(s)).collect(Collectors.toList()));

        Set<String> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);

    }
} 
