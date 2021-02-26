package com.gamingmatcher.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String email,username,password,confirmPassword;
}
