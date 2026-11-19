package com.krishna.machinecoding.employee;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String id) {
        super("No employee found with id: " + id);
    }
}
