package com.example.filmex.controller;

import com.example.filmex.config.jwt.JwtProvider;
import com.example.filmex.model.User;
import com.example.filmex.request.SecurityRequest;
import com.example.filmex.response.AuthResponse;
import com.example.filmex.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(final UserService userService, final JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody final SecurityRequest request) {
        final User user = new User();
        user.setPassword(request.getPassword());
        user.setLogin(request.getLogin());
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody final SecurityRequest request) {
        final User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        final String token = jwtProvider.generateToken(user.getLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(token));
    }
}
