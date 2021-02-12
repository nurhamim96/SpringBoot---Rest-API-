package com.test.msemployeeservice.service;

import com.test.msemployeeservice.model.request.CreateEmployeeRequest;
import com.test.msemployeeservice.model.response.EmployeeResponse;
import com.test.msemployeeservice.model.request.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(CreateEmployeeRequest request);

    EmployeeResponse get(String id);

    EmployeeResponse update(UpdateEmployeeRequest request);

    List<EmployeeResponse> list();

    void delete(String id);

}
