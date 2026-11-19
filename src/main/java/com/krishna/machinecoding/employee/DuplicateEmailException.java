package com.krishna.machinecoding.employee;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("An employee with email already exists: " + email);
    }
}
