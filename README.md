# Machine Coding Starter

A runnable Spring Boot base prepared for live machine coding rounds. The goal:
open the IDE, start the app, and be writing business logic from minute one
instead of wiring frameworks.

## Stack

- Java 17, Spring Boot 3, Maven
- Spring Web (REST), Bean Validation
- Lombok for boilerplate
- RestTemplate preconfigured for outbound HTTP calls
- SLF4J/Logback logging configured in `application.yml`
- In-memory storage behind a repository interface (no database needed)

## Layout

```
src/main/java/com/krishna/machinecoding
├── Application.java                  entry point
├── config/
│   └── AppConfig.java                RestTemplate bean with timeouts
├── employee/                         sample vertical slice (replace or extend live)
│   ├── Employee.java                 domain model
│   ├── CreateEmployeeRequest.java    inbound DTO with validation
│   ├── EmployeeResponse.java         outbound DTO (domain model never exposed)
│   ├── EmployeeRepository.java       storage boundary (interface)
│   ├── InMemoryEmployeeRepository.java  ConcurrentHashMap implementation
│   ├── EmployeeService.java          business logic
│   └── EmployeeController.java       HTTP layer only
└── exception/
    └── GlobalExceptionHandler.java   consistent JSON error bodies
```

## Run

```bash
mvn spring-boot:run
```

App starts on `http://localhost:8080`.

## Try it

Create:

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Asha Rao","email":"asha@example.com","department":"Payroll"}'
```

Fetch one:

```bash
curl http://localhost:8080/api/employees/<id-from-create-response>
```

List all:

```bash
curl http://localhost:8080/api/employees
```

Validation failure (400):

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"","email":"nope","department":""}'
```

Duplicate email (409): POST the same payload twice.

Unknown id (404):

```bash
curl http://localhost:8080/api/employees/does-not-exist
```

## Tests

```bash
mvn test
```

`EmployeeApiTest` boots the full app on a random port and exercises
create-then-fetch plus the validation path.

## Design notes

- Controller -> Service -> Repository interface -> in-memory implementation.
  Swapping in a real database later means writing one new repository
  implementation, nothing above it changes.
- Request and response DTOs are separate from the domain model, so the API
  contract is independent of internal representation.
- Errors are mapped in one `@RestControllerAdvice` so every failure returns
  the same JSON shape.
