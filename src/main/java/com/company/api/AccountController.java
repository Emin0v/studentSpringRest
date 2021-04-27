package com.company.api;

import com.company.dto.*;
import com.company.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService service;

    @PostMapping("/add/user")
    public ResponseEntity<ResponseDTO> createStudent(@RequestBody RegisterDTO registerDTO){
        UserDTO userDTO = service.register(registerDTO);
        return ResponseEntity.ok(ResponseDTO.of(userDTO,"Successfully added"));
    }

}
