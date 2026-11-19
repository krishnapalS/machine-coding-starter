package com.krishna.machinecoding.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Inbound API payload. Validation runs before the controller body executes.
 */
public record CreateEmployeeRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid address")
        String email,

        @NotBlank(message = "department is required")
        String department
) {
}
