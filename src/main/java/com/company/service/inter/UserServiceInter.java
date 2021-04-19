package com.company.service.inter;

import com.company.dto.UserDTO;
import com.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInter {

    User createUser(UserDTO userDTO);

    boolean deleteUser(Integer id);

    Optional<User> findById(Integer id);

    public List<User> getAll(String name, String surname);

    public Optional<User> findByUsernameAndPassword(String email, String password);

    public Optional<User> findByUsername(String email);

}
