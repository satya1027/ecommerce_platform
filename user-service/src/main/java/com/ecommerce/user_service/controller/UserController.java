package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.UserRegistrationRequest;
import com.ecommerce.user_service.dto.UserResponse;
import com.ecommerce.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegistrationRequest request) {
        return userService.register(request);
    }
}
