package com.mx.aplazo.BNPL.service;

import com.mx.aplazo.BNPL.dto.AuthSignUpResponse;
import com.mx.aplazo.BNPL.exception.NotFoundCustomerException;
import com.mx.aplazo.BNPL.exception.UsernameAlreadyTakenException;
import com.mx.aplazo.BNPL.model.User;
import com.mx.aplazo.BNPL.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public AuthSignUpResponse createUser(String username, String password) {
        AuthSignUpResponse authSignUpResponse = new AuthSignUpResponse();
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyTakenException("username is already taken!");
        }
        User newUser = new User(
                null,
                username,
                encoder.encode(password)
        );
        userRepository.save(newUser);
        System.out.println("User created with username: " + username);
        authSignUpResponse.setMessage("User created successfully!");
        return authSignUpResponse;
    }
}
