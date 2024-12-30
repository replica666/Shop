package com.alten.shop.service;

import com.alten.shop.dto.request.UserReqDto;
import com.alten.shop.dto.response.UserResDto;
import com.alten.shop.entities.User;

import java.util.Optional;

public interface UserService {

    UserResDto createUser(UserReqDto userDTO);

    Optional<User> findUserByEmail(String email);

    User getCurrentUser();
}
