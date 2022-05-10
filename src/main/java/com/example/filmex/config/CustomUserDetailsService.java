package com.example.filmex.config;

import com.example.filmex.model.User;
import com.example.filmex.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userService.findByLogin(username);
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}
