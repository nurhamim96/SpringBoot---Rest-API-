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
public class EmployeeRequest {
    private String id;

    private Date birthDate;

    private String firstName;

    private String lastName;

    private String gender;

    private Date hireDate;
}
