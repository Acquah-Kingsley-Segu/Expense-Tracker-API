package com.kingsley.springboot.expense_tracker.repository;

import com.kingsley.springboot.expense_tracker.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, String> {
    Optional<OTP> findOTPByOtp(int otp);
}
