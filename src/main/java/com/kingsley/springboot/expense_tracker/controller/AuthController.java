package com.kingsley.springboot.expense_tracker.controller;


import com.kingsley.springboot.expense_tracker.dto.LoginDTO;
import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;
import com.kingsley.springboot.expense_tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tracker")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> signup(@RequestBody SystemUserDTO newUser){
        String response = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginCredentials){
        System.out.println("Login");
        Object response = userService.authenticateUser(loginCredentials);
        return ResponseEntity.ok(response);
    }
}
