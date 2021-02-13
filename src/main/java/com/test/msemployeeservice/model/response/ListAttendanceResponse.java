package com.test.msemployeeservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAttendanceResponse {
    private String id;

    private Date dateOfWork;

    private String employeeId;

    private String firstName;

    private String lastName;

    private Date checkIn;

    private Date checkOut;
}
