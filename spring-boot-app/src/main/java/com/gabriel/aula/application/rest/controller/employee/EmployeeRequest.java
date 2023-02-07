package com.gabriel.aula.application.rest.controller.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeRequest {
    private String name;
    private Integer age;
}
