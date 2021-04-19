package com.company.repository;

import com.company.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRole(String role);

}
