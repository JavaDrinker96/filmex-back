package com.example.filmex.service;

import com.example.filmex.model.User;

public interface UserService {

    User saveUser(User user);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
