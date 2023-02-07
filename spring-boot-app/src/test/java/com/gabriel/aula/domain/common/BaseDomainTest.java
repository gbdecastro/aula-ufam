package com.gabriel.aula.domain.common;

import com.gabriel.aula.domain.employee.Employee;

public class BaseDomainTest {

    protected Employee employeeTest;

    protected void givenEmployee(Long id, String name, Integer age){
        employeeTest = Employee.builder().id(id).name(name).age(age).build();
    }
}
