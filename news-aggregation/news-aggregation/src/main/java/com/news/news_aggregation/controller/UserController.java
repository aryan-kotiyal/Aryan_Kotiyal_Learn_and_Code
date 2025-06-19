package com.news.news_aggregation.controller;

import com.news.news_aggregation.dto.*;
//import com.news.news_aggregation.security.JwtUtil;
import com.news.news_aggregation.model.Role;
import com.news.news_aggregation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
//    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserSignupRequest request) {
        return ResponseEntity.ok(userService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request) {
        UserResponse user = userService.login(request);
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//        return ResponseEntity.ok(token);
        UserResponse response = UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(Role.valueOf(user.getRole().name()))
                .build();
        return ResponseEntity.ok(response);
    }
}
