package com.gabriel.aula.domain.employee;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper {
    Employee update(@MappingTarget Employee employee, Employee employeeToUpdate);
}
