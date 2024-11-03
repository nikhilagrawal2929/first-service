package com.espire.practice;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee1 {
    private int empId;
    private String empName;
    private String empAddress;
    private String city;

    public String check(String s) {
        return "s";
    }

    public String check() {
        System.out.println("Hello");
        return "";
    }
    public void  check1(String s) {
        System.out.println(s);
    }
}
