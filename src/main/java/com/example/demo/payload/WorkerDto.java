package com.example.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "worker name is not found")
    private String name;

    @NotNull(message = "phone number not found ")
    private String phoneNumber;

    @NotNull(message = "enter street")
    private String street;

    @NotNull(message = "enter home number")
    private Integer  homeNumber;

    @NotNull(message = "Enter department id")
    private Integer departmentId;

}
