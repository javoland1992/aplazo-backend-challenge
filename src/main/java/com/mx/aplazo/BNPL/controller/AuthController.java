package com.mx.aplazo.BNPL.controller;

import com.mx.aplazo.BNPL.dto.AuthRequest;
import com.mx.aplazo.BNPL.dto.AuthSignInResponse;
import com.mx.aplazo.BNPL.dto.AuthSignUpResponse;
import com.mx.aplazo.BNPL.service.UserService;
import com.mx.aplazo.BNPL.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController
@Tag(name = "AuthController", description = "Authentication and Authorization")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/signin")
    @Operation(summary = "Create a user, whomes will create JWT tokens", security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<AuthSignInResponse> authenticateUser(@RequestBody AuthRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(),
                        userRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AuthSignInResponse authSignInResponse = new AuthSignInResponse();
        authSignInResponse.setAccess_token(jwtUtils.generateToken(userDetails.getUsername()));
        authSignInResponse.setToken_type("Bearer");
        authSignInResponse.setExpires_in(3600000);
        return ResponseEntity.status(HttpStatus.CREATED).body(authSignInResponse);
    }

    @PostMapping("/signup")
    @Operation(summary = "Create a JWT with a valid user", security = { @SecurityRequirement(name = "bearerAuth") })
    public ResponseEntity<AuthSignUpResponse> createUser(@RequestBody AuthRequest authRequest) {
        AuthSignUpResponse authSignUpResponse = userService.createUser(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(authSignUpResponse);
    }
}