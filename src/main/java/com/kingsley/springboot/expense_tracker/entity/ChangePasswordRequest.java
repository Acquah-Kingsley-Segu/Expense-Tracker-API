package com.kingsley.springboot.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime tokenExpiration;
    @ManyToOne(targetEntity = SystemUser.class)
    @JoinColumn(name = "user_id")
    private SystemUser user;
}
