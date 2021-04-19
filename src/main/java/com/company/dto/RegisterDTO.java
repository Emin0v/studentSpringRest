package com.company.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO extends LoginDTO{

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private String age;


}
