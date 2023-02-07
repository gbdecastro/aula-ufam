package com.gabriel.aula.application.rest.controller.employee;

import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class EmployeeLinks {
    public static final Class<EmployeeController> controller = EmployeeController.class;

    public static Link findAll() {
        return linkTo(methodOn(controller).findAll()).withRel("find-all");
    }

    public static Link findOne(Long id) {
        return linkTo(methodOn(controller).findOne(id)).withRel("find-one");
    }

    public static Link create(EmployeeRequest employeeRequest) {
        return linkTo(methodOn(controller).create(employeeRequest)).withRel("create");
    }

    public static Link update(Long id, EmployeeRequest employeeRequest) {
        return linkTo(methodOn(controller).update(id, employeeRequest)).withRel("update");
    }

    public static Link delete(Long id) {
        return linkTo(methodOn(controller).delete(id)).withRel("delete");
    }
}
