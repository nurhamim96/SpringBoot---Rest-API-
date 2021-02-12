package com.test.msemployeeservice.model.request;

import com.test.msemployeeservice.config.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank(message = "NotBlank")
    private Date birthDate;

    @NotBlank(message = "NotBlank")
    private String firstName;

    @NotBlank(message = "NotBlank")
    private String lastName;

    @NotBlank(message = "NotBlank")
    private Gender gender;

    @NotBlank(message = "NotBlank")
    private Date hireDate;

//    @NotBlank(message = "NotBlank")
//    private Date createdAt;
//
//    @NotBlank(message = "NotBlank")
//    private Date updateAt;
}
