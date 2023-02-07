package com.gabriel.aula.application.rest.controller.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeResponse {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
