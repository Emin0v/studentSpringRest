package com.company.service.impl;

import com.company.entity.User;
import com.company.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findByUsername(username).get();
        if (user != null) {
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);

            builder.disabled(false);
            builder.password(user.getPassword());

            String[] authoritiesArr = new String[]{user.getRole().getRole()};
            builder.authorities(authoritiesArr);

            return builder.build();
        } else {
            throw new UsernameNotFoundException("Email " + username + " not found");
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = service.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " not found"));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                getAuthorities(user));
//    }
//
//    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        String[] userRoles = user.getRoles().stream().map((role) -> role.getRole()).toArray(String[]::new);
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
//    }

}
