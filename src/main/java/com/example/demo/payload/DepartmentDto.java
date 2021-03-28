package com.example.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {


    @NotNull(message = "Please enter the department name")
    private String name;


    @NotNull(message = "Company id is not entered")
    private Integer companyId;

}
