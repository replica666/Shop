package com.alten.shop.service.Impl;

import com.alten.shop.dto.request.UserReqDto;
import com.alten.shop.dto.response.UserResDto;
import com.alten.shop.entities.User;
import com.alten.shop.exceptions.EmailAlreadyExistsException;
import com.alten.shop.exceptions.ResourceNotFoundException;
import com.alten.shop.mappers.UserMapper;
import com.alten.shop.repository.UserRepository;
import com.alten.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResDto createUser(UserReqDto userDTO) {
        if (findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .firstname(userDTO.getFirstname())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        return userMapper.toDto(userRepository.save(user));
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        String username = authentication.getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", username));
    }
}
