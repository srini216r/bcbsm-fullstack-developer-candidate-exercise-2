package com.user.UserApplication.controller;

import com.user.UserApplication.dto.UserDto;
import com.user.UserApplication.entity.User;
import com.user.UserApplication.service.UserService;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerOrUpdateUser(@Valid @RequestBody UserDto userDto) throws ParseException {
        User registeredUser = userService.registerOrUpdateUser(userDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
         return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }



}
