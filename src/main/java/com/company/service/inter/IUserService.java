package com.company.service.inter;

import com.company.dto.RegisterDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDTO register(RegisterDTO registerDTO);

    boolean deleteUser(Integer id);

    public boolean check(String username);

    Optional<User> findById(Integer id);

    public List<UserDTO> getAll(String name, String surname);

    public Optional<User> findByUsernameAndPassword(String email, String password);

    public Optional<User> findByUsername(String email);

}
