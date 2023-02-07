package com.gabriel.aula.application.rest.controller.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeApi {

    @Operation(summary = "Get all Employees")
    @ApiResponse(responseCode = "200", description = "Found Employees")
    ResponseEntity<CollectionModel<EmployeeResponse>> findAll();

    @Operation(summary = "Find a Employee by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),})
    ResponseEntity<EntityModel<EmployeeResponse>> findOne(@PathVariable @Parameter(description = "An id of Employee") Long id);

    @Operation(summary = "Create a Employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Employee created"),
            @ApiResponse(responseCode = "400", description = "Somethings was wrong!")})
    ResponseEntity<EntityModel<EmployeeResponse>> create(@RequestBody EmployeeRequest officialStampRequest);

    @Operation(summary = "Update a Employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Employee updated"),
            @ApiResponse(responseCode = "400", description = "Somethings was wrong!"),
            @ApiResponse(responseCode = "404", description = "Employee not found")})
    ResponseEntity<EntityModel<EmployeeResponse>> update(@PathVariable @Parameter(description = "An id of Employee") Long id,
                                                              @RequestBody EmployeeRequest officialStampRequest);

    @Operation(summary = "Delete a Employee by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),})
    ResponseEntity<Void> delete(@PathVariable @Parameter(description = "An id of Employee") Long id);
}
