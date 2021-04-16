package com.company.dto;

import com.company.entity.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDTO {

   private Integer id;
   private String name;

   public RoleDTO(Roles roles){
      this.id= roles.getId();
      this.name = roles.getRole();
   }


}
