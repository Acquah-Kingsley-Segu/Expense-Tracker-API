package com.kingsley.springboot.expense_tracker.service.implementations;

import com.kingsley.springboot.expense_tracker.config.CustomAuthentication;
import com.kingsley.springboot.expense_tracker.config.SecurityUser;
import com.kingsley.springboot.expense_tracker.dto.LoginDTO;
import com.kingsley.springboot.expense_tracker.dto.SystemUserDTO;
import com.kingsley.springboot.expense_tracker.entity.SystemUser;
import com.kingsley.springboot.expense_tracker.repository.UserRepository;
import com.kingsley.springboot.expense_tracker.service.JWTService;
import com.kingsley.springboot.expense_tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final SecurityUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
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

    @Override
    public Object authenticateUser(LoginDTO loginCredentials) {
        SecurityUser userDetail = (SecurityUser) userDetailService.loadUserByUsername(loginCredentials.getEmail());
        if (!passwordEncoder.matches(loginCredentials.getPassword(), userDetail.getPassword()))
            throw new AuthenticationServiceException("Wrong credentials");
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication(userDetail.getUsername(), true));
        return jwtService.generateToken(new HashMap<>(), loginCredentials.getEmail());
    }
}
