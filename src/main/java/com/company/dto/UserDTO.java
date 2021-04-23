package com.company.dto;

import com.company.entity.Role;
import com.company.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String username;
    private List<RoleDTO> roles;
    private List<TaskDTO> taskDTOS;


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.username = user.getUsername();

        List<RoleDTO> roleDTOS = new ArrayList<>();

        List<Role> roleList = user.getRoles();

        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            roleDTOS.add(new RoleDTO(role));
        }
        roles = roleDTOS;
    }

    public User toUser() {
        User user = new User();

        user.setId(getId());
        user.setName(getName());
        user.setSurname(getSurname());
        user.setAge(getAge());
        user.setUsername(getUsername());

        List<Role> roleList = new ArrayList<>();
        List<RoleDTO> roleDTOS = getRoles();

        for (int i = 0; i < roleDTOS.size(); i++) {
            RoleDTO roleDTO = roleDTOS.get(i);

            roleList.add(roleDTO.toRole());
        }

        user.setRoles(roleList);

        return user;

    }
}
