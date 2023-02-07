package com.gabriel.aula.api.rest.controller.employee;

import com.gabriel.aula.api.rest.common.BaseIntegrationTest;
import com.gabriel.aula.application.rest.controller.employee.EmployeeController;
import com.gabriel.aula.application.rest.controller.employee.EmployeeRequest;
import com.gabriel.aula.domain.employee.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_AGE;
import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class EmployeeControllerIT extends BaseIntegrationTest {

    private final String BASE_URI = EmployeeController.API_URL;
    private final String BASE_URI_ID = EmployeeController.API_URL.concat("/{id}");

    @AfterAll
    void cleanUp() {
        employeeRepository.deleteAll();
    }

    // region tests

    @Test
    @DisplayName("Getting all employees: Should Return all employees")
    void findAll_shouldReturnAll() throws Exception {
        givenEmployee(EMPLOYEE_NAME, EMPLOYEE_AGE);
        whenRequestFindAll();
        thenShouldReturnAll();
    }
    // endregion tests


    // region when request
    private void whenRequestFindAll() throws Exception {
        perform = mockMvc.perform(get(BASE_URI));
    }

    private void whenRequestFindOne(Long id) throws Exception {
        perform = mockMvc.perform(get(BASE_URI_ID, id));
    }

    private void whenRequestCreate(EmployeeRequest employeeRequest) throws Exception {

        MockHttpServletRequestBuilder requestBuilder =
                post(BASE_URI).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(employeeRequest));


        perform = mockMvc.perform(requestBuilder);
    }

    private void whenRequestUpdate(Long id, EmployeeRequest employeeRequest) throws Exception {

        MockHttpServletRequestBuilder requestBuilder =
                put(BASE_URI_ID, id).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(employeeRequest));


        perform = mockMvc.perform(requestBuilder);
    }

    private void whenRequestDelete(Long id) throws Exception {
        perform = mockMvc.perform(delete(BASE_URI_ID, id));
    }

    // endregion when request

    // region then
    private void thenShouldReturnAll() throws Exception {
        perform.andExpect(status().isOk()).andExpect(jsonPath("$._embedded.employeeResponseList").isArray())
                .andExpect(jsonPath("$._embedded.employeeResponseList[0].name").value(employeeTest.getName()))
                .andExpect(jsonPath("$._embedded.employeeResponseList[0].age").value(employeeTest.getAge()));
    }
    // endregion then


}
