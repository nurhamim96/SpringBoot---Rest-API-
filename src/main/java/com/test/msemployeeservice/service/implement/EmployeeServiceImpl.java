package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.model.request.CreateEmployeeRequest;
import com.test.msemployeeservice.model.response.EmployeeResponse;
import com.test.msemployeeservice.model.request.UpdateEmployeeRequest;
import com.test.msemployeeservice.repository.EmployeeRepository;
import com.test.msemployeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {

        var date = new Date();

        Employee employee = Employee.builder()
                .birthDate(request.getBirthDate())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .hireDate(request.getHireDate())
                .createdAt(date)
                .updateAt(null)
                .build();

        employee = employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .id(employee.getId())
                .birthDate(employee.getBirthDate())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .hireDate(employee.getHireDate())
                .createdAt(employee.getCreatedAt())
                .updateAt(employee.getUpdateAt())
                .build();
    }

    @Override
    public EmployeeResponse get(String employeeNo) {
        Optional<Employee> employee = employeeRepository.findById(employeeNo);
        return EmployeeResponse.builder()
                .id(employee.get().getId())
                .birthDate(employee.get().getBirthDate())
                .firstName(employee.get().getFirstName())
                .lastName(employee.get().getLastName())
                .hireDate(employee.get().getHireDate())
                .createdAt(employee.get().getCreatedAt())
                .updateAt(employee.get().getUpdateAt())
                .build();
    }

    @Override
    public EmployeeResponse update(UpdateEmployeeRequest request) {

        var created = get(request.getId());

        var date = new Date();

            Employee data = Employee.builder()
                    .id(request.getId())
                    .birthDate(request.getBirthDate())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .hireDate(request.getHireDate())
                    .gender(request.getGender())
                    .createdAt(created.getCreatedAt())
                    .updateAt(date)
                    .build();
            data = employeeRepository.save(data);

        return EmployeeResponse.builder()
                .id(data.getId())
                .birthDate(data.getBirthDate())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .hireDate(data.getHireDate())
                .gender(data.getGender())
                .createdAt(data.getCreatedAt())
                .updateAt(data.getUpdateAt())
                .build();
    }

    @Override
    public List<EmployeeResponse> list() {
        return employeeRepository.findAll().stream()
                .map(value -> EmployeeResponse.builder()
                        .id(value.getId())
                        .birthDate(value.getBirthDate())
                        .firstName(value.getFirstName())
                        .lastName(value.getLastName())
                        .hireDate(value.getHireDate())
                        .gender(value.getGender())
                        .createdAt(value.getCreatedAt())
                        .updateAt(value.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employeeRepository.delete(employee.get());
    }
}
