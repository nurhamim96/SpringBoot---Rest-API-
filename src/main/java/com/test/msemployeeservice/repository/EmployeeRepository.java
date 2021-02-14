package com.test.msemployeeservice.repository;

import com.test.msemployeeservice.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Employee update(Employee employee);

    Optional<Employee> findById(String id);

    List<Employee> findAll();

    void delete(String id);
}
