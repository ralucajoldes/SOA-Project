package com.toie.userService.service;

import com.toie.userService.domain.User;
import com.toie.userService.mapper.UserMapper;
import com.toie.userService.model.LoginRequestDto;
import com.toie.userService.model.LoginResponseDto;
import com.toie.userService.model.RegisterRequestDto;
import com.toie.userService.model.RegisterResponseDto;
import com.toie.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        var user = userMapper.map(registerRequestDto, UUID.randomUUID(), passwordEncoder.encode(registerRequestDto.getPassword()));
        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return new RegisterResponseDto().token(token);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                ));
        var user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();

        var token = jwtService.generateToken(user);

        return new LoginResponseDto().token(token);
    }
}
