package com.test.msemployeeservice.service;

import com.test.msemployeeservice.model.CreateEmployeeRequest;
import com.test.msemployeeservice.model.EmployeeResponse;
import com.test.msemployeeservice.model.UpdateEmployeeRequest;

import javax.validation.Valid;
import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(CreateEmployeeRequest request);

    EmployeeResponse get(String employeeNo);

    EmployeeResponse update(UpdateEmployeeRequest request);

    List<EmployeeResponse> list();

    void delete(String id);

}
