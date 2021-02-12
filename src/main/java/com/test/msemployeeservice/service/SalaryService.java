package com.test.msemployeeservice.service;


import com.test.msemployeeservice.model.request.CreateSalaryRequest;
import com.test.msemployeeservice.model.request.UpdateSalaryRequest;
import com.test.msemployeeservice.model.response.SalaryResponse;

import java.util.List;

public interface SalaryService {

    SalaryResponse create(CreateSalaryRequest request);

    SalaryResponse get(String id);

    SalaryResponse update(UpdateSalaryRequest request);

    List<SalaryResponse> list();

    void delete(String id);
}
