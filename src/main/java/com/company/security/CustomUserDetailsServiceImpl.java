package com.company.security;

import com.company.entity.User;
import com.company.entity.UserRole;
import com.company.exception.UserNotFound;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceInter service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username);
        if (user != null) {
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);

            builder.disabled(false);
            builder.password(user.getPassword());

            List<UserRole> list = user.getUserRoleList();
            List<String> roleList = new ArrayList<String>();

            for(UserRole userRole : list){
                roleList.add(userRole.getRoleId().getRole());
            }

            String[] authoritiesArr = new String[roleList.size()];

            authoritiesArr = roleList.toArray(authoritiesArr);
//            String[] authoritiesArr = new String[]{"ADMIN","USER"};
            builder.authorities(authoritiesArr);

            for(String s : authoritiesArr)
                System.out.println(s);

            return builder.build();
        } else {
            throw new UserNotFound("user not found");
        }
    }
}
