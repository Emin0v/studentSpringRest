package com.company.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank
    @Email
    String username;

    @NotBlank
    @Size(min = 6)
    String password;
}
