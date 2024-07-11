package com.kingsley.springboot.expense_tracker.controller;


import com.kingsley.springboot.expense_tracker.dto.LoginDTO;
import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;
import com.kingsley.springboot.expense_tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tracker/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SystemUserDTO newUser){
        String response = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginCredentials){
        Object response = userService.authenticateUser(loginCredentials);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/account/verification")
    public ResponseEntity<String> otpVerification(@RequestParam String otp){
        String response = userService.verifyOTP(otp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/request/change-password")
    public ResponseEntity<String> changePasswordRequest(@RequestParam String email){
        String response = userService.verifyChangePasswordEmail(email);
        return ResponseEntity.ok(response);
    }
}
