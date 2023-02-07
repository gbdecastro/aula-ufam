package com.gabriel.aula.application.rest.controller.employee;

import com.gabriel.aula.domain.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse toEmployeeResponse(Employee employee);

    List<EmployeeResponse> toEmployeeResponseList(Collection<Employee> employeeCollection);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Employee toEmployee(EmployeeRequest employeeRequest);

}