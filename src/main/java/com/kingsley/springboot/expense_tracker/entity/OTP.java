package com.kingsley.springboot.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(nullable = false)
    private Integer otp;
    @OneToOne(targetEntity = SystemUser.class)
    private SystemUser user;
}
