package com.krishna.machinecoding.employee;

import java.util.List;
import java.util.Optional;

/**
 * Repository boundary. The service layer only talks to this interface,
 * so swapping the in-memory implementation for JPA/JDBC later touches
 * nothing above it.
 */
public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(String id);

    List<Employee> findAll();

    boolean existsByEmail(String email);
}
