package com.test.msemployeeservice.controller;

import com.test.msemployeeservice.model.WebResponse;
import com.test.msemployeeservice.model.request.CheckInAttendanceRequest;
import com.test.msemployeeservice.model.request.CheckoutAttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.model.response.CheckInAndCheckOutResponse;
import com.test.msemployeeservice.model.response.ListAttendanceResponse;
import com.test.msemployeeservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(value = "/check-in", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> create(@RequestBody CheckInAttendanceRequest request) {
        CheckInAndCheckOutResponse attendanceResponse = attendanceService.checkIn(request);

        return WebResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .data(attendanceResponse)
                .build();
    }

    @PutMapping(value = "/check-out", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<?> update(@RequestBody CheckoutAttendanceRequest request) {
        CheckInAndCheckOutResponse attendanceResponse = attendanceService.checkOut(request);

        return WebResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(attendanceResponse)
                .build();
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<ListAttendanceResponse>> attendanceEmployees() {
        List<ListAttendanceResponse> attendanceResponses = attendanceService.list();

        return WebResponse.<List<ListAttendanceResponse>>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(attendanceResponses)
                .build();
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> delete(@PathVariable("id") String id) {
        attendanceService.delete(id);

        return WebResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .status("OK")
                .data("ID : " + id + " Has been deleted")
                .build();
    }
}
