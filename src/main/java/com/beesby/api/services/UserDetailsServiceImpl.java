package com.beesby.api.services;

import com.beesby.api.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        User user = userService.findById(UUID.fromString(userId));
        return new org.springframework.security.core.userdetails.User(user.getCpf(), getPassword(), getAuthorities());
    }

    private String getPassword() {
        return "password";
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
