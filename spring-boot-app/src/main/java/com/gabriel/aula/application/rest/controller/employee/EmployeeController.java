package com.gabriel.aula.application.rest.controller.employee;

import com.gabriel.aula.application.rest.annotation.BaseController;
import com.gabriel.aula.domain.employee.Employee;
import com.gabriel.aula.domain.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@BaseController(EmployeeController.API_URL)
public class EmployeeController implements EmployeeApi{
    public static final String API_URL = "/api/employees";

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EmployeeResponse>> findAll() {
        List<Employee> employees = service.findAll();

        List<EmployeeResponse> employeeResponses = mapper.toEmployeeResponseList(employees);

        Link self = linkTo(methodOn(getClass()).findAll()).withSelfRel();
        Link create = EmployeeLinks.create(null);
        Link update = EmployeeLinks.update(null, null);
        Link delete = EmployeeLinks.delete(null);
        Link findOne = EmployeeLinks.findOne(null);

        CollectionModel<EmployeeResponse> entityModel = CollectionModel.of(employeeResponses, self, create, update, delete, findOne);

        return ResponseEntity.ok(entityModel);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeeResponse>> findOne(Long id) {
        EmployeeResponse employeeResponse = mapper.toEmployeeResponse(service.findById(id));

        Link self = linkTo(methodOn(getClass()).findOne(id)).withSelfRel();
        Link update = EmployeeLinks.update(id, null);
        Link delete = EmployeeLinks.delete(id);

        EntityModel<EmployeeResponse> entityModel = EntityModel.of(employeeResponse, self, update, delete);

        return ResponseEntity.ok(entityModel);
    }

    @Override
    @PostMapping
    public ResponseEntity<EntityModel<EmployeeResponse>> create(EmployeeRequest employeeRequest) {
        Employee employee = mapper.toEmployee(employeeRequest);
        EmployeeResponse employeeResponse = mapper.toEmployeeResponse(service.create(employee));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeResponse.getId()).toUri();

        Link selfLink = linkTo(methodOn(getClass()).create(employeeRequest)).withSelfRel();
        Link findOne = EmployeeLinks.findOne(employeeResponse.getId());
        Link update = EmployeeLinks.update(employeeResponse.getId(), null);
        Link delete = EmployeeLinks.delete(employeeResponse.getId());

        EntityModel<EmployeeResponse> entityModel = EntityModel.of(employeeResponse, selfLink, findOne, update, delete);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeeResponse>> update(Long id, EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse =
                mapper.toEmployeeResponse(service.update(id, mapper.toEmployee(employeeRequest)));

        Link selfLink = linkTo(methodOn(getClass()).update(id, employeeRequest)).withSelfRel();
        Link findOne = EmployeeLinks.findOne(id);
        Link delete = EmployeeLinks.delete(id);

        EntityModel<EmployeeResponse> entityModel = EntityModel.of(employeeResponse, selfLink, findOne, delete);

        return ResponseEntity.ok(entityModel);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
