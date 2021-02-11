package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.model.CreateEmployeeRequest;
import com.test.msemployeeservice.model.EmployeeResponse;
import com.test.msemployeeservice.model.UpdateEmployeeRequest;
import com.test.msemployeeservice.repository.EmployeeRepository;
import com.test.msemployeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    Date date = new Date();

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {

        Employee employee = Employee.builder()
//                .employeeNo(UUID.randomUUID().toString())
                .birthDate(request.getBirthDate())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .hireDate(request.getHireDate())
//                .createdAt(date)
                .build();

        employee = employeeRepository.save(employee);
        return EmployeeResponse.builder()
                .employeeNo(employee.getEmployeeNo())
                .birthDate(employee.getBirthDate())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .hireDate(employee.getHireDate())
                .createdAt(employee.getCreatedAt())
                .build();
    }

    @Override
    public EmployeeResponse get(String employeeNo) {
        Optional<Employee> employee = employeeRepository.findById(employeeNo);
        return EmployeeResponse.builder()
                .employeeNo(employee.get().getEmployeeNo())
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

            Employee data = Employee.builder()
                    .employeeNo(request.getEmployeeNo())
                    .birthDate(request.getBirthDate())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .hireDate(request.getHireDate())
                    .gender(request.getGender())
                    .build();
            employeeRepository.save(data);

        return EmployeeResponse.builder()
                .employeeNo(request.getEmployeeNo())
                .birthDate(request.getBirthDate())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .hireDate(request.getHireDate())
                .gender(request.getGender())
                .build();
    }

    @Override
    public List<EmployeeResponse> list() {
        return employeeRepository.findAll().stream()
                .map(value -> EmployeeResponse.builder()
                        .employeeNo(value.getEmployeeNo())
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
    public void delete(String employeeNo) {
        Optional<Employee> employee = employeeRepository.findById(employeeNo);
        employeeRepository.delete(employee.get());
    }
}
