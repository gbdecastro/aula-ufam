package com.gabriel.aula.domain.employee;

import com.gabriel.aula.common.ParameterizedAgeTest;
import com.gabriel.aula.domain.common.BaseDomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_AGE;
import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_AGE_2;
import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_ID;
import static com.gabriel.aula.common.EmployeeConstant.EMPLOYEE_NAME;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest extends BaseDomainTest {

    // BDD (GivenWhenThen) - Martin Flower
    // https://martinfowler.com/bliki/GivenWhenThen.html

    private boolean isAdultResult;

    @Test
    @DisplayName("Testando se um funcionário é maior de idade")
    void isAdult_shouldReturnTrue() {
        // Dado um funcionário
        givenEmployee(EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_AGE);
        // Executado a ação: é adulto?
        whenIsAdult();
        // Então deveria retornar true
        thenShouldReturnTrue();
    }

    @Test
    @DisplayName("Testando se um funcionário NÃO é maior de idade")
    void isAdult_shouldReturnFalse() {
        // Dado um funcionário
        givenEmployee(EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_AGE_2);
        // Executado a ação: é adulto?
        whenIsAdult();
        // Então deveria retornar false
        thenShouldReturnFalse();
    }

    @ParameterizedAgeTest
    @DisplayName("Testando se um funcionario é ou não maior de idade")
    void isAdult_shouldReturnAccordingFromParameter(Integer age) {
        givenEmployee(EMPLOYEE_ID, EMPLOYEE_NAME, age);
        whenIsAdult();
        if(age >= 18){
            thenShouldReturnTrue();
        } else {
            thenShouldReturnFalse();
        }

    }

    /**
     * Método responsável por chamar ação da qual espero um resultado para ser validado
     */
    private void whenIsAdult() {
        isAdultResult = employeeTest.isAdult();
    }

    /**
     * Método responsável por validar se o retorno da ação foi como o esperado (maior de idade)
     */
    private void thenShouldReturnTrue() {
        assertTrue(isAdultResult);
    }

    /**
     * Método responsável por validar se o retorno da ação foi como o esperado (menor de idade)
     */
    private void thenShouldReturnFalse() {
        assertFalse(isAdultResult);
    }



}
