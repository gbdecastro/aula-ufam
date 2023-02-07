package com.gabriel.aula.domain.employee;

import com.gabriel.aula.domain.common.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.gabriel.aula.domain.employee.EmployeeConstant.EMPLOYEE_NOT_FOUND;
import static com.gabriel.aula.domain.employee.EmployeeConstant.NAME_INVALID;
import static com.gabriel.aula.domain.employee.EmployeeConstant.SEPARATOR_COMMA;

@Service
@Lazy
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeEntityMapper mapper;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMPLOYEE_NOT_FOUND));
    }

    @Override
    public Employee create(Employee employee) {
        validateEmployee(employee);
        repository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Long id, Employee toUpdate) {
        Employee employee = findById(id);

        employee = mapper.update(employee, toUpdate);

        validateEmployee(employee);

        repository.saveAndFlush(employee);

        return employee;

    }

    @Override
    public void delete(Long id) {
        Employee employee = findById(id);
        repository.delete(employee);
    }

    private void validateEmployee(Employee employee) {
        List<String> errors = new ArrayList<>();

        if(StringUtils.isEmpty(employee.getName())){
            errors.add(NAME_INVALID);
        }

        if (!errors.isEmpty()) {
            throw new ServiceException(String.join(SEPARATOR_COMMA, errors));
        }
    }
}
