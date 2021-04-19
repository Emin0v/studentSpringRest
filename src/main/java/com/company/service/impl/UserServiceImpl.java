package com.company.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dto.UserDTO;
import com.company.entity.Role;
import com.company.entity.User;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInter {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public User register(UserDTO userDTO) {

        User user = userDTO.toUser();

        user.setPassword(crypt.hashToString(4, user.getPassword().toCharArray()));

        String mail = user.getUsername().split("@")[1];

        List<Role> roles = new ArrayList<>();

        if (mail.equals("admin.edu")) {
            Role role = roleRepository.findByRole("ADMIN");
            roles.add(role);
        } else if (mail.equals("teacher.edu")) {
            Role role = roleRepository.findByRole("TEACHER");
            roles.add(role);
        } else {
            Role role = roleRepository.findByRole("STUDENT");
            roles.add(role);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean check(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll(String name, String surname) {
        return userRepository.getAll(name, surname);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String email, String password) {
        return userRepository.findByUsernameAndPassword(email, password);
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepository.findByUsername(email);
    }
}
