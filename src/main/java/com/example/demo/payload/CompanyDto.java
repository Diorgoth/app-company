package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "Please enter company name")
    private String  corpName;

    @NotNull(message = "Please enter directer name")
    private String  directorName;

    @NotNull(message = "Please enter street")
    private String street;

    @NotNull(message = "Please enter home number")
    private Integer  homeNumber;

}
