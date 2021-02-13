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
public class CheckInAndCheckOutResponse {
    private String id;

    private Date dateOfWork;

    private String salaryId;

    private String employeeId;

    private Date checkIn;

    private Date checkOut;
}
