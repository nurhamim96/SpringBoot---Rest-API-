package com.test.msemployeeservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInAttendanceRequest {

    @NotBlank(message = "NotBlank")
    private Date dateOfWork;

    @NotBlank(message = "NotBlank")
    private String salaryId;

    @NotBlank(message = "NotBlank")
    private String  employeeId;

}
