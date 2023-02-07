package com.gabriel.aula.api.rest.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.aula.domain.employee.Employee;
import com.gabriel.aula.domain.employee.EmployeeRepository;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    protected ResultActions perform;

    // region data
    protected Long INVALID_ID = -1L;
    protected Employee employeeTest;
    // endregion data

    // region repositories
    @Autowired
    protected EmployeeRepository employeeRepository;
    // endregion repositories

    protected void givenEmployee(String name, Integer age){
        employeeTest = Employee.builder().name(name).age(age).build();
        employeeRepository.save(employeeTest);
    }
}
