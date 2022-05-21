package com.example.filmex.service;

import com.example.filmex.exception.AuthenticationException;
import com.example.filmex.exception.NotFoundException;
import com.example.filmex.exception.NullParameterException;
import com.example.filmex.model.Role;
import com.example.filmex.model.User;
import com.example.filmex.repository.RoleRepository;
import com.example.filmex.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(final UserRepository userRepository,
                           final RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(final User user) {
        if (Objects.isNull(user)) {
            throw new NullParameterException("User entity can't be null");
        }
        final Role userRole = Optional.ofNullable(roleRepository.findByName("ROLE_USER"))
                .orElseThrow(() -> new NotFoundException(Role.class.getTypeName() + " is not exist"));

        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByLogin(final String login) {
        if (Objects.isNull(login)) {
            throw new NullParameterException("Login can't be null");
        }
        return Optional.ofNullable(userRepository.findByLogin(login))
                .orElseThrow(() -> new AuthenticationException("Can't find user with login = " + login));
    }

    @Override
    public User findByLoginAndPassword(final String login, final String password) {
        if (Objects.isNull(login) || Objects.isNull(password)) {
            throw new NullParameterException("Login or password can't be null");
        }
        final User user = findByLogin(login);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Password is not correct.");
        }
        return user;
    }
}
