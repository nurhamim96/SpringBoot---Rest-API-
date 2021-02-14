package com.test.msemployeeservice.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {
    private String id;

    private Date dateOfWork;

    private String salaryId;

    private String  employeeId;

    private String firstName;

    private String lastName;

    private Date checkIn;

    private Date checkOut;

}
