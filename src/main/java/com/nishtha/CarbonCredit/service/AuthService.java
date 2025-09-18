package com.nishtha.CarbonCredit.service;




import com.nishtha.CarbonCredit.DTOs.LoginResponse;
import com.nishtha.CarbonCredit.DTOs.OTPRequest;
import com.nishtha.CarbonCredit.DTOs.SignUpRequest;
import com.nishtha.CarbonCredit.Security.JwtUtil;
import com.nishtha.CarbonCredit.Util.OTPUtil;
import com.nishtha.CarbonCredit.entity.User;
import com.nishtha.CarbonCredit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    public User signup(SignUpRequest req) {
        String role = "ROLE_" + req.getRole().trim().toUpperCase();
        User user = User.builder()
                .role(role)
                .aadhar(req.getAadhar())
                .email(req.getEmail().toLowerCase())
                .phone(req.getPhone())
                .build();
        user.setId(null);
        return userRepo.save(user);
    }

    public void sendOtp(String email) {
        User user = userRepo.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        String otp = OTPUtil.generateOtp();
        user.setOTPCode(otp);
        user.setOTPExpiry(LocalDateTime.now().plusMinutes(5));
        userRepo.save(user);
        emailService.sendOtpEmail(user.getEmail(), otp);
    }

    public LoginResponse verifyOtp(OTPRequest req) {
        User user = userRepo.findByEmail(req.getEmail().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        System.out.println("DEBUG => Stored OTP: " + user.getOTPCode() +
                " | Provided: " + req.getOtp());
        System.out.println("DEBUG => Expiry: " + user.getOTPExpiry() +
                " | Now: " + LocalDateTime.now());

        if (user.getOTPCode() == null
                || user.getOTPExpiry() == null
                || !user.getOTPCode().equals(req.getOtp())
                || user.getOTPExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        // Optional: clear OTP after login
        user.setOTPCode(null); user.setOTPExpiry(null);
        userRepo.save(user);
        return new LoginResponse(token, user.getRole());
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

