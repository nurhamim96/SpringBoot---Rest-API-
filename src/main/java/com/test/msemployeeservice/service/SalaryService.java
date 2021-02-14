package com.test.msemployeeservice.service;


import com.test.msemployeeservice.model.request.SalaryRequest;
import com.test.msemployeeservice.model.response.SalaryResponse;

import java.util.List;

public interface SalaryService {

    SalaryResponse create(SalaryRequest request);

    SalaryResponse get(String id);

    SalaryResponse update(SalaryRequest request);

    List<SalaryResponse> list();

    void delete(String id);
}
