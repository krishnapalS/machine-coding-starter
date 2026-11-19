package com.krishna.machinecoding.employee;

import java.time.Instant;

/**
 * Outbound API payload. The API never exposes the domain model directly.
 */
public record EmployeeResponse(
        String id,
        String name,
        String email,
        String department,
        Instant createdAt
) {

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getCreatedAt()
        );
    }
}
