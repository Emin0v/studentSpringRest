package com.company.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dto.RegisterDTO;
import com.company.dto.UserDTO;
import com.company.entity.Role;
import com.company.entity.StudentRank;
import com.company.entity.User;
import com.company.repository.RoleRepository;
import com.company.repository.StudentRankRepository;
import com.company.repository.UserRepository;
import com.company.service.inter.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final StudentRankRepository studentRankRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public UserDTO register(RegisterDTO registerDTO) {

            String username = registerDTO.getUsername();
            if (check(username)) {
                throw new ClientAlreadyExistsException("User already exist");
            }

            User user = registerDTO.toUser();
            user.setPassword(crypt.hashToString(4, user.getPassword().toCharArray()));

            String mail = user.getUsername().split("@")[1];

            if (mail.equals("admin.edu")) {
                Role role = roleRepository.findByRole("ADMIN");
                user.setRole(role);
            } else if (mail.equals("teacher.edu")) {
                Role role = roleRepository.findByRole("TEACHER");
                user.setRole(role);
            } else {
                Role role = roleRepository.findByRole("STUDENT");
                user.setRole(role);
            }
            User savedUser = userRepository.save(user);

            if(savedUser.getRole().getRole().equals("STUDENT")){
                StudentRank sr = new StudentRank() ;
                sr.setRank(10);
                sr.setStudentId(savedUser);

                studentRankRepository.save(sr);
            }

            return new UserDTO(savedUser);

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
    public List<UserDTO> getAll(String name, String surname) {
        List<User> users =  userRepository.getAll(name, surname);
        return Arrays.asList(modelMapper.map(users, UserDTO[].class));
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
