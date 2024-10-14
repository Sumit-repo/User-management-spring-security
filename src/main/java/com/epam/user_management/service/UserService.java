package com.epam.user_management.service;

import com.epam.user_management.dto.requestDto.UserRequestDto;
import com.epam.user_management.dto.responseDto.UserResponseDto;
import com.epam.user_management.entity.Role;
import com.epam.user_management.entity.User;
import com.epam.user_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserResponseDto signUpUser(UserRequestDto requestDto) {
        User user = convertToUserEntity(requestDto);
        User savedUser = userRepository.save(user);
        log.info("Saved user details: {}", savedUser);
        return convertToUserResponseDto(savedUser);
    }

    public String signInUser(UserRequestDto requestDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        return tokenService.generateToken(authentication);
    }

    private User convertToUserEntity(UserRequestDto requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .roles(List.of(Role.USER.getAuthority()))
                .build();
    }

    private UserResponseDto convertToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
