package com.kingsley.springboot.expense_tracker.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SystemUserDTO {
    private String name;
    private String email;
    private String password;
}
