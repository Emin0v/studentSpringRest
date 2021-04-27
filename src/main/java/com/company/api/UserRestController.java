package com.company.api;


import com.company.config.AppConfig;
import com.company.dto.RegisterDTO;
import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.IUserService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService service;

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer id){
        Optional<User> user =  service.findById(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user.get())));
    }




}
