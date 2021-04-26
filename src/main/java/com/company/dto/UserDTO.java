package com.company.dto;

import com.company.entity.Role;
import com.company.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String username;
    private RoleDTO role;


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.username = user.getUsername();
        this.role = new RoleDTO(user.getRole());

    }

    public User toUser() {
        User user = new User();

        user.setId(getId());
        user.setName(getName());
        user.setSurname(getSurname());
        user.setAge(getAge());
        user.setUsername(getUsername());
        user.setRole(getRole().toRole());

        return user;

    }
}
