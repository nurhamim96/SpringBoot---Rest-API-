package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Salary;
import com.test.msemployeeservice.model.request.CreateSalaryRequest;
import com.test.msemployeeservice.model.request.UpdateSalaryRequest;
import com.test.msemployeeservice.model.response.SalaryResponse;
import com.test.msemployeeservice.repository.SalaryRepository;
import com.test.msemployeeservice.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public SalaryResponse create(CreateSalaryRequest request) {

        Salary salary = Salary.builder()
                .salary(request.getSalary())
                .build();

        salary = salaryRepository.save(salary);
        return SalaryResponse.builder()
                .id(salary.getId())
                .salary(salary.getSalary())
                .build();
    }

    @Override
    public SalaryResponse get(String id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        return SalaryResponse.builder()
                .id(salary.get().getId())
                .salary(salary.get().getSalary())
                .build();
    }

    @Override
    public SalaryResponse update(UpdateSalaryRequest request) {

        Salary salary = Salary.builder()
                .id(request.getId())
                .salary(request.getSalary())
                .build();
        salary = salaryRepository.save(salary);

        return SalaryResponse.builder()
                .id(salary.getId())
                .salary(salary.getSalary())
                .build();
    }

    @Override
    public List<SalaryResponse> list() {
        return salaryRepository.findAll().stream()
                .map(salary -> SalaryResponse.builder()
                        .id(salary.getId())
                        .salary(salary.getSalary())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Optional<Salary> salary = salaryRepository.findById(id);
        salaryRepository.delete(salary.get());
    }
}
