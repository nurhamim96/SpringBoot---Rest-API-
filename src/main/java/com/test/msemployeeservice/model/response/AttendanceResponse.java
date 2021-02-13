package com.test.msemployeeservice.model.response;

import com.test.msemployeeservice.config.constant.Gender;
import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
    private String id;

    private Date dateOfWork;

    private String salaryId;

    private String employeeId;

    private String firstName;

    private String lastName;

    private Integer totalWorkingDays;

    private Long salary;

    private Long totalSalary;

    private Date checkIn;

    private Date checkOut;

}
