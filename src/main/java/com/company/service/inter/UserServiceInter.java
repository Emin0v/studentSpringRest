package com.company.service.inter;

import com.company.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInter {

    User createUser(User user);

    boolean deleteUser(Integer id);

    Optional<User> findById(Integer id);

    public List<User> getAll(String name, String surname);

    public User findByUsernameAndPassword(String email, String password);

    public User findByUsername(String email);

}
