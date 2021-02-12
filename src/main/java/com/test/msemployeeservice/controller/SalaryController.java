package com.test.msemployeeservice.controller;

import com.test.msemployeeservice.model.WebResponse;
import com.test.msemployeeservice.model.request.CreateSalaryRequest;
import com.test.msemployeeservice.model.request.UpdateSalaryRequest;
import com.test.msemployeeservice.model.response.SalaryResponse;
import com.test.msemployeeservice.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> create(@RequestBody CreateSalaryRequest request) {
        SalaryResponse salaryResponse = salaryService.create(request);

        return WebResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .data(salaryResponse)
                .build();
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> update(@RequestBody UpdateSalaryRequest request) {
        SalaryResponse salaryResponse = salaryService.update(request);

        return WebResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(salaryResponse)
                .build();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<SalaryResponse>> salaries() {
        List<SalaryResponse> salaryResponses = salaryService.list();

        return WebResponse.<List<SalaryResponse>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(salaryResponses)
                .build();

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<SalaryResponse> get(@PathVariable("id") String id) {
        SalaryResponse salaryResponse = salaryService.get(id);

        return WebResponse.<SalaryResponse>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(salaryResponse)
                .build();
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> delete(@PathVariable("id") String id) {
        salaryService.delete(id);

        return WebResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .status("OK")
                .data("ID : " + id + " Has been deleted")
                .build();
    }
}
