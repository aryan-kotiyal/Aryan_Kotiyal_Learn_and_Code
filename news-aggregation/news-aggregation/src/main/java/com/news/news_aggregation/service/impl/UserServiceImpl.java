package com.news.news_aggregation.service.impl;

import com.news.news_aggregation.dto.*;
import com.news.news_aggregation.model.*;
import com.news.news_aggregation.repository.UserRepository;
import com.news.news_aggregation.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponse signup(UserSignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
