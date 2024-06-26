package com.kingsley.springboot.expense_tracker.repository;

import com.kingsley.springboot.expense_tracker.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, Integer> {
    Optional<SystemUser> findSystemUserByEmail(String email);
}
