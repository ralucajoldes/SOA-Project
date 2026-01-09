package com.toie.userService.controller;

import com.toie.userService.api.UserApi;
import com.toie.userService.model.LoginRequestDto;
import com.toie.userService.model.LoginResponseDto;
import com.toie.userService.model.RegisterRequestDto;
import com.toie.userService.model.RegisterResponseDto;
import com.toie.userService.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AuthenticationController implements UserApi {

    private final AuthenticationService authenticationService;


    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        var loginResponse = authenticationService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto registerRequestDto) {
        var registerResponse = authenticationService.register(registerRequestDto);
        return ResponseEntity.ok(registerResponse);
    }
}
