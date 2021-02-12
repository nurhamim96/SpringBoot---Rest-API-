package com.test.msemployeeservice.model.request;

import com.test.msemployeeservice.config.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @NotBlank(message = "NotBlank")
    private String id;

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

}
