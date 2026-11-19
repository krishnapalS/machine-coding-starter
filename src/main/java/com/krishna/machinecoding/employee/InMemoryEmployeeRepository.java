package com.krishna.machinecoding.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * In-memory implementation backed by a ConcurrentHashMap.
 * Structured exactly as a database-backed repository would be:
 * same interface, same semantics, no storage concerns leaking upward.
 */
@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final ConcurrentMap<String, Employee> store = new ConcurrentHashMap<>();

    @Override
    public Employee save(Employee employee) {
        store.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(e -> e.getEmail().equalsIgnoreCase(email));
    }
}
