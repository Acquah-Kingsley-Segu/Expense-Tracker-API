package com.kingsley.springboot.expense_tracker.repository;

import com.kingsley.springboot.expense_tracker.entity.ChangePasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangePasswordRequestRepository extends JpaRepository<ChangePasswordRequest, String> {
}
