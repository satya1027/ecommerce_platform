package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.UserRegistrationRequest;
import com.ecommerce.user_service.dto.UserResponse;

public interface UserService {

    UserResponse register(UserRegistrationRequest request);
}
