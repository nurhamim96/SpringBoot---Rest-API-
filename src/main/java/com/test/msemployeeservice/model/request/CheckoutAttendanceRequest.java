package com.test.msemployeeservice.model.request;

import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutAttendanceRequest {
    private String id;

    private Date dateOfWork;

    private String salaryId;

    private String  employeeId;

}
