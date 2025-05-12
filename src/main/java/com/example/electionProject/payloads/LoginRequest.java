package com.example.electionProject.payloads;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String userType;
}
