package com.test.msemployeeservice.controller;

import com.test.msemployeeservice.model.request.CreateEmployeeRequest;
import com.test.msemployeeservice.model.response.EmployeeResponse;
import com.test.msemployeeservice.model.request.UpdateEmployeeRequest;
import com.test.msemployeeservice.model.WebResponse;
import com.test.msemployeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> create(@RequestBody CreateEmployeeRequest request) {
        EmployeeResponse employeeResponse = employeeService.create(request);

        return WebResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .data(employeeResponse)
                .build();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> update(@RequestBody UpdateEmployeeRequest request) {
        EmployeeResponse employeeResponse = employeeService.update(request);

        return WebResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(employeeResponse)
                .build();

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<EmployeeResponse>> employees() {
        List<EmployeeResponse> employeeResponses = employeeService.list();

        return WebResponse.<List<EmployeeResponse>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(employeeResponses)
                .build();
    }

    @GetMapping(value = "/{employeeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<EmployeeResponse> get(@PathVariable("employeeNo") String employeeNo) {
        EmployeeResponse employeeResponse = employeeService.get(employeeNo);

        return WebResponse.<EmployeeResponse>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(employeeResponse)
                .build();
    }

    @DeleteMapping(value = "/delete/{employeeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> delete(@PathVariable("employeeNo") String employeeNo) {
        employeeService.delete(employeeNo);
        return WebResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .status("OK")
                .data("ID : " + employeeNo + " Has been deleted")
                .build();
    }
}
