package com.company.dto;

import com.company.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDTO {

   private Integer id;
   private String name;

   public RoleDTO(Role role){
      this.id= role.getId();
      this.name = role.getRole();
   }

   public Role toRole(){
      Role role = new Role();
      role.setId(getId());
      role.setRole(getName());

      return role;
   }


}
