package com.company.dto;

import com.company.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterDTO extends LoginDTO {

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    @Size(min = 3)
    private String surname;

    @NotNull
    private Integer age;

    public RegisterDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toUser(){
        User user = new User();

        user.setName(getName());
        user.setSurname(getSurname());
        user.setAge(getAge());
        user.setUsername(getUsername());
        user.setPassword(getPassword());

        return user;
    }


}
