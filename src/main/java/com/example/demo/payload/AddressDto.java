package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {


    @NotNull(message = "street kiritilishi kerak")
    private String street;

    @NotNull(message = "UY raqami kiritilishi kerak")
    private Integer  homeNumber;

}
