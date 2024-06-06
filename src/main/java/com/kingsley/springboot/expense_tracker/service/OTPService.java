package com.kingsley.springboot.expense_tracker.service;

import com.kingsley.springboot.expense_tracker.entity.OTP;
import com.kingsley.springboot.expense_tracker.entity.SystemUser;

public interface OTPService {
    OTP generateVerificationOTP(SystemUser user);

    String verifyOTP(String otp);
}
