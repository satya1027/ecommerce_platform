package com.ecommerce.user_service.service.impl;

import com.ecommerce.user_service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String register() {
        return "User Registered Successfully";
    }
}
