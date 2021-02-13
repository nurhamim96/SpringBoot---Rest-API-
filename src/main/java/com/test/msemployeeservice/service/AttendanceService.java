package com.test.msemployeeservice.service;

import com.test.msemployeeservice.model.request.CheckInAttendanceRequest;
import com.test.msemployeeservice.model.request.CheckoutAttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.model.response.CheckInAndCheckOutResponse;
import com.test.msemployeeservice.model.response.ListAttendanceResponse;

import java.util.List;

public interface AttendanceService {

    CheckInAndCheckOutResponse checkIn(CheckInAttendanceRequest request);

    AttendanceResponse get(String id);

    CheckInAndCheckOutResponse checkOut(CheckoutAttendanceRequest request);

    List<ListAttendanceResponse> list();

    void delete(String id);
}
