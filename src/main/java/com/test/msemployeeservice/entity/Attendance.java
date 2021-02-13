package com.test.msemployeeservice.entity;

import com.test.msemployeeservice.config.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance_employee")
public class Attendance {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Date dateOfWork;

    @Column(name = "salary_id")
    private String salaryId;

    @Column(name = "employee_id", nullable = false)
    private String  employeeId;

    private Date birthDate;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date hireDate;

    @Column(name = "total_working_days", columnDefinition="int(10) default 0")
    private Integer totalWorkingDays = 0;

    private Long salary;

    private Long totalSalary;

    @Column(name = "check_in", updatable = false)
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;
}
