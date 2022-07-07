package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.*;
import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.controller.documentation.AuthContollerDoc;
import com.alkemy.ong.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthContollerDoc {

    private UserDetailsCustomService userDetailsCustomService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public AuthController(UserDetailsCustomService userDetailsCustomService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegistration(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        UserResponseDto userResponseDto = userDetailsCustomService.register(userRequestDto);
        AuthenticationRequest authRequest = new AuthenticationRequest(userRequestDto.getEmail(),userRequestDto.getPassword());
        AuthenticationResponse authResponse = this.userDetailsCustomService.login(authRequest);
        return ResponseEntity.ok().body(authResponse);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) {
        AuthenticationResponse authResponse = this.userDetailsCustomService.login(authRequest);
        return ResponseEntity.ok().body(authResponse);
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getProfile(HttpServletRequest request) {
        UserResponseDto responseDto = userDetailsCustomService.getProfile(request);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
