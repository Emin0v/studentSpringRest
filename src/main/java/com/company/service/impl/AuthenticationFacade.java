package com.company.service.impl;

import com.company.entity.User;
import com.company.repository.UserRepository;
import com.company.service.inter.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getCurrentUser(){
        Authentication authentication = getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        return user;
    }
}
