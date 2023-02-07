package com.gabriel.aula.application.rest.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
}