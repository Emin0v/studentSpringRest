package com.company.controller;


import com.company.config.AppConfig;
import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class UserRestController {

    private final AppConfig app;

    private final UserServiceInter service;

    @GetMapping("/details")
    public String getAppDetails(){
        return app.getName()+" Version : "+app.getVersion() ;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer id){
        Optional<User> user =  service.findById(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user.get())));
    }

    @PostMapping("/add/user")
    public void createStudent(@RequestBody User user){
        service.createUser(user);
    }



}
