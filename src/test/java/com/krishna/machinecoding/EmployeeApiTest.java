package com.krishna.machinecoding;

import com.krishna.machinecoding.employee.CreateEmployeeRequest;
import com.krishna.machinecoding.employee.EmployeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createThenFetchEmployee() {
        var request = new CreateEmployeeRequest("Asha Rao", "asha@example.com", "Payroll");

        ResponseEntity<EmployeeResponse> created =
                restTemplate.postForEntity("/api/employees", request, EmployeeResponse.class);

        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        assertThat(created.getBody().id()).isNotBlank();

        ResponseEntity<EmployeeResponse> fetched =
                restTemplate.getForEntity("/api/employees/" + created.getBody().id(), EmployeeResponse.class);

        assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetched.getBody()).isNotNull();
        assertThat(fetched.getBody().email()).isEqualTo("asha@example.com");
    }

    @Test
    void rejectsInvalidPayload() {
        var request = new CreateEmployeeRequest("", "not-an-email", "");

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/employees", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
