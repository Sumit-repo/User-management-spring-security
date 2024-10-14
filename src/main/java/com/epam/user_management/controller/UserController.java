package com.epam.user_management.controller;

import com.epam.user_management.dto.requestDto.UserRequestDto;
import com.epam.user_management.dto.responseDto.UserResponseDto;
import com.epam.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    String getUser() {
        return "Hi unauthorized user";
    }

    @GetMapping("authorizedUsers")
    String getAuthZUser() {
        return "Hi authorized user";
    }

    @GetMapping("authorizedForAdmin")
    @PreAuthorize("hasRole('Administrator')")
    String getDetails() {
        return "Hi, i am authorized as admin";
    }

    @PostMapping("signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUpUser(requestDto));
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signInUser(requestDto));
    }
}
