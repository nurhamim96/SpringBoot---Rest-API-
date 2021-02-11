package com.test.msemployeeservice.entity;

import com.test.msemployeeservice.config.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String employeeNo;

    private Date birthDate;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date hireDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updateAt;

}
