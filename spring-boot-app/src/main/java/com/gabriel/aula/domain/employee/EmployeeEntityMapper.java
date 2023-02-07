package com.gabriel.aula.domain.employee;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeEntityMapper {
    @Mapping(target = "id", ignore = true)
    Employee update(@MappingTarget Employee employee, Employee employeeToUpdate);
}
