package com.company.dto;

import com.company.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRoleDTO {

    private Integer id;
    private RoleDTO roleDTO;

    public UserRoleDTO(UserRole role){
        this.id = role.getId();
        this.roleDTO = new RoleDTO(role.getRoleId());
    }

}
