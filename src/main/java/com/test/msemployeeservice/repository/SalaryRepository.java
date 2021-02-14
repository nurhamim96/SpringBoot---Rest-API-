package com.test.msemployeeservice.repository;

import com.test.msemployeeservice.entity.Salary;
import com.test.msemployeeservice.model.response.SalaryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SalaryRepository {

    Salary save(Salary salary);

    Salary update(Salary salary);

    Optional<Salary> findById(String id);

    List<Salary> findAll();

    void delete(String id);

}
