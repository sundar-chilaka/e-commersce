package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.LoginRequestpojo;
import com.ecom.entity.UserEntity;
import com.ecom.jwt.JwtUtil;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	 private final AuthenticationManager authenticationManager;
	    private final UserService userService;
	    private final JwtUtil jwtUtil;

	    @Autowired
	    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
	        this.authenticationManager = authenticationManager;
	        this.userService = userService;
	        this.jwtUtil = jwtUtil;
	    }

	    @PostMapping("/signup")
	    public String register(@RequestBody UserEntity user) {
	        userService.registerUser(user);
	        return "User registered successfully";
	    }

	    @PostMapping("/login")
	    public String login(@RequestBody LoginRequestpojo request) {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	        return jwtUtil.generateToken(request.getUsername());
	    }
	}