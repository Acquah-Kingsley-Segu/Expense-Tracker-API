package com.kingsley.springboot.expense_tracker.service;

public interface EmailService {
    void sendMail(String to, String subject, String body);
}
