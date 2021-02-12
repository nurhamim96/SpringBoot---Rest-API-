package com.test.msemployeeservice.repository;

import com.test.msemployeeservice.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {
}
