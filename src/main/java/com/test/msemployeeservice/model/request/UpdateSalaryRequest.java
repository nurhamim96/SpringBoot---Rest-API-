package com.test.msemployeeservice.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSalaryRequest {
    @NotBlank(message = "NotBlank")
    private String id;

    @NotBlank(message = "NotBlank")
    private Long salary;

    @NotBlank(message = "NotBlank")
    private Long totalSalary;
}
