package com.nishtha.CarbonCredit.controller;


import com.nishtha.CarbonCredit.DTOs.LoginResponse;
import com.nishtha.CarbonCredit.DTOs.OTPRequest;
import com.nishtha.CarbonCredit.DTOs.SignUpRequest;
import com.nishtha.CarbonCredit.entity.User;
import com.nishtha.CarbonCredit.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody @Valid SignUpRequest req) {
        return authService.signup(req);
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        authService.sendOtp(email);
        return "OTP sent";
    }

    @PostMapping("/verify-otp")
    public LoginResponse verifyOtp(@RequestBody @Valid OTPRequest req) {
        return authService.verifyOtp(req);
    }
}

