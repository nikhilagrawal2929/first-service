package com.espire.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice"),
            new Employee(2, "Bob"),
            new Employee(3, "Charlie"),
            new Employee(4, "David"),
            new Employee(5, "Alex")
        );

        List list =employees.stream().sorted((e1,e2)->e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
        List list2 =employees.stream().sorted((e1,e2)->e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(employees.stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
        // Sort employees by empName
      /*  List<Employee> sortedEmployees = employees.stream()
                .sorted((e1, e2) -> e1.getEmpName().compareTo(e2.getEmpName()))
                .collect(Collectors.toList());

        // Print the sorted employees
        sortedEmployees.forEach(System.out::println);*/
    }
}
