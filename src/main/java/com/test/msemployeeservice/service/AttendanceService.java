package com.test.msemployeeservice.service;



import com.test.msemployeeservice.model.request.AttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.model.response.ListAttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse checkIn(AttendanceRequest request);

    AttendanceResponse get(String id);

    AttendanceResponse checkOut(AttendanceRequest request);

    List<ListAttendanceResponse> list();

    void delete(String id);
}
