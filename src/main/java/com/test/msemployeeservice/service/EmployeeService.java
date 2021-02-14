package com.test.msemployeeservice.service;

import com.test.msemployeeservice.model.request.EmployeeRequest;
import com.test.msemployeeservice.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse get(String id);

    EmployeeResponse update(EmployeeRequest request);

    List<EmployeeResponse> list();

    void delete(String id);

}
