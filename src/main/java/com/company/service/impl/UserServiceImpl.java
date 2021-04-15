package com.company.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import com.company.repository.UserRepository;
import com.company.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInter {

    private final UserRepository userRepository;

    public static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public User createUser(User user) {
        user.setPassword(crypt.hashToString(4, user.getPassword().toCharArray()));
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll(String name, String surname) {
        return userRepository.getAll(name,surname);
    }

    @Override
    public User findByUsernameAndPassword(String email, String password) {
        return userRepository.findByUsernameAndPassword(email,password);
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }
}
