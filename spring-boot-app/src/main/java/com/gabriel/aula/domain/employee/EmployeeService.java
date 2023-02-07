package com.gabriel.aula.domain.employee;

import java.util.List;

public interface EmployeeService {
    /**
     * Find all {@link Employee}
     * @return a list of {@link Employee}
     */
    List<Employee> findAll();

    /**
     * Find a {@link Employee} by id
     * @param id an id of {@link Employee}
     * @return a {@link Employee}
     */
    Employee findById(Long id);

    /**
     * Create a new {@link Employee}
     * @param employee a {@link Employee}
     * @return the created {@link Employee}
     */
    Employee create(Employee employee);

    /**
     * Update a {@link Employee}
     * @param id an id of {@link Employee}
     * @param toUpdate a {@link Employee}
     * @return the updated {@link Employee}
     */
    Employee update(Long id, Employee toUpdate);

    /**
     * Delete a {@link Employee} by id
     * @param id an id of {@link Employee}
     */
    void delete(Long id);
}
