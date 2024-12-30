package com.alten.shop.service.Impl;


import com.alten.shop.dto.request.LoginRequest;
import com.alten.shop.dto.request.UserReqDto;
import com.alten.shop.dto.response.TokenResponse;
import com.alten.shop.dto.response.UserResDto;
import com.alten.shop.entities.User;
import com.alten.shop.exceptions.InvalidCredentialsException;
import com.alten.shop.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserResDto createAccount(UserReqDto userReqDto) {
        return userService.createUser(userReqDto);
    }

    public TokenResponse getToken(LoginRequest request) {

        User user = userService.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String jwt = jwtService.generateToken(user);

        return TokenResponse.builder()
                .token(jwt)
                .user(userMapper.toDto(user))
                .build();
    }

}
