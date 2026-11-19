package com.krishna.machinecoding.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Domain model. Kept persistence-agnostic: the repository interface is
 * what would change if a real database were introduced, not this class.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String id;
    private String name;
    private String email;
    private String department;
    private Instant createdAt;
}
