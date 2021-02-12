package com.test.msemployeeservice.service;

import com.test.msemployeeservice.model.request.CheckInAttendanceRequest;
import com.test.msemployeeservice.model.request.CheckoutAttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse checkIn(CheckInAttendanceRequest request);

    AttendanceResponse get(String id);

    AttendanceResponse checkOut(CheckoutAttendanceRequest request);

    List<AttendanceResponse> list();

    void delete(String id);
}
