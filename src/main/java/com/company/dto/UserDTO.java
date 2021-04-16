package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserRole;
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
    private String password;
    private List<UserRoleDTO> userRoleList;


    public UserDTO(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.surname=user.getSurname();
        this.age=user.getAge();
        this.username=user.getUsername();
        this.password=user.getPassword();

        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();

        List<UserRole> userRoles = user.getUserRoleList();

//        for(int i=0 ; i<userRoles.size() ; i++){
//            UserRole userRole = userRoles.get(i);
//            userRoleDTOS.add(new UserRoleDTO())
//        }


    }
}
