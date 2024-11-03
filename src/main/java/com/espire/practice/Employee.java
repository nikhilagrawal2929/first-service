package com.espire.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor // Default constructor
@AllArgsConstructor // Constructor with all fields
public class Employee implements Serializable {
    private int empId;
    private String empName;
}
