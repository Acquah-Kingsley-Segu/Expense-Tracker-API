package com.kingsley.springboot.expense_tracker.service;

import com.kingsley.springboot.expense_tracker.dto.LoginDTO;
import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;

public interface UserService {
    String createNewUser(SystemUserDTO newUser);

    Object authenticateUser(LoginDTO loginCredentials);

    String verifyOTP(String otp);

    String verifyChangePasswordEmail(String email);
}
