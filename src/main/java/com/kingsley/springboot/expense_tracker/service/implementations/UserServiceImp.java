package com.kingsley.springboot.expense_tracker.service.implementations;

import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;
import com.kingsley.springboot.expense_tracker.entity.SystemUser;
import com.kingsley.springboot.expense_tracker.repository.UserRepository;
import com.kingsley.springboot.expense_tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String createNewUser(SystemUserDTO newUser) {
        SystemUser user = SystemUser.builder()
                .email(newUser.getEmail())
                .name(newUser.getName())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .build();
        try{
            userRepository.save(user);
            return "Account created successfully.";
        }catch (Exception exception){
            return exception.getMessage();
        }
    }
}
