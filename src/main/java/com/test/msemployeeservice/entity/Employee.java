package com.test.msemployeeservice.entity;;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String id;

    private Date birthDate;

    private String firstName;

    private String lastName;

    private String gender;

    private Date hireDate;

    private Long totalWorkingDays;

    private Long totalSalary;

    private Date createdAt;

    private Date updateAt;

}

