package com.kingsley.springboot.expense_tracker.controller;

import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;
import com.kingsley.springboot.expense_tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tracker/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SystemUserDTO newUser){
        String response = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
