package com.krishna.machinecoding.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Business logic lives here, not in the controller. The controller only
 * translates HTTP to service calls and back.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse create(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }

        Employee employee = Employee.builder()
                .id(UUID.randomUUID().toString())
                .name(request.name())
                .email(request.email())
                .department(request.department())
                .createdAt(Instant.now())
                .build();

        employeeRepository.save(employee);
        log.info("Created employee id={} department={}", employee.getId(), employee.getDepartment());
        return EmployeeResponse.from(employee);
    }

    public EmployeeResponse getById(String id) {
        return employeeRepository.findById(id)
                .map(EmployeeResponse::from)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::from)
                .toList();
    }
}
