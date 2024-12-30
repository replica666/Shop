package com.alten.shop.controllers;


import com.alten.shop.controllers.api.AuthApi;
import com.alten.shop.dto.request.LoginRequest;
import com.alten.shop.dto.request.UserReqDto;
import com.alten.shop.dto.response.TokenResponse;
import com.alten.shop.dto.response.UserResDto;
import com.alten.shop.service.Impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthServiceImpl authService;

    @PostMapping("/account")
    public ResponseEntity<UserResDto> createAccount(@RequestBody @Valid UserReqDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.createAccount(request));
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.getToken(request));
    }

}
