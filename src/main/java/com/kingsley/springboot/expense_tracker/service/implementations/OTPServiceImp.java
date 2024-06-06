package com.kingsley.springboot.expense_tracker.service.implementations;

import com.kingsley.springboot.expense_tracker.entity.OTP;
import com.kingsley.springboot.expense_tracker.entity.SystemUser;
import com.kingsley.springboot.expense_tracker.repository.OTPRepository;
import com.kingsley.springboot.expense_tracker.repository.UserRepository;
import com.kingsley.springboot.expense_tracker.service.OTPService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class OTPServiceImp implements OTPService {
    private final OTPRepository otpRepository;
    private final UserRepository userRepository;

    @Override
    public OTP generateVerificationOTP(SystemUser user) {
        Random random = new Random();
        OTP otp = OTP.builder()
                .otp(1000 + random.nextInt(9000))
                .user(user)
                .build();
        return otpRepository.save(otp);
    }

    @Override
    public String verifyOTP(String otp) {
        Optional<OTP> fetchOTP =  otpRepository.findOTPByOtp(Integer.parseInt(otp));
        if (fetchOTP.isEmpty())
            return "Invalid token";
        else{
            assert fetchOTP.get().getUser() != null : "You do not have account";
            SystemUser user = fetchOTP.get().getUser();
            user.setAccountVerified(true);
            userRepository.save(user);
            return "Account verified successfully";
        }
    }
}
