package com.example.filmex.util;

import com.example.filmex.config.CustomUserDetails;
import com.example.filmex.model.User;
import com.example.filmex.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final UserService userService;

    public UserUtil(final UserService userService) {
        this.userService = userService;
    }

    public Long getCurrentUserId(){
        final CustomUserDetails cud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByLogin(cud.getUsername()).getId();
    }
}
