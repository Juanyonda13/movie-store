package com.movies_shop.movies_shop.controller;

import com.movies_shop.movies_shop.config.JwtUtil;
import com.movies_shop.movies_shop.model.dto.UserDto;
import com.movies_shop.movies_shop.model.entity.User;
import com.movies_shop.movies_shop.model.payload.MessageResponse;
import com.movies_shop.movies_shop.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            User savedUser = userService.save(userDto);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("User registered successfully")
                    .object(savedUser)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Error: " + e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );
            String token = jwtUtil.generateToken(userDto.getEmail());
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Login successful")
                    .object(token)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Invalid credentials")
                    .build(), HttpStatus.UNAUTHORIZED);
        }
    }
}
