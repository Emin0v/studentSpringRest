package com.company.controller;


import com.company.config.AppConfig;
import com.company.domain.Student;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add/user")
    public void createStudent(@RequestBody User user){
        service.createUser(user);
    }

}
