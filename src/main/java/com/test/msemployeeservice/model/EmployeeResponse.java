package com.test.msemployeeservice.model;

import com.test.msemployeeservice.config.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String employeeNo;

    private Date birthDate;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Date hireDate;

    private Date createdAt;

    private Date updateAt;
}
