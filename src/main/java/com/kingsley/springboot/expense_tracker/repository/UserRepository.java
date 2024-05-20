package com.kingsley.springboot.expense_tracker.repository;

import com.kingsley.springboot.expense_tracker.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SystemUser, String> {
    SystemUser findSystemUserByName(String name);
}
