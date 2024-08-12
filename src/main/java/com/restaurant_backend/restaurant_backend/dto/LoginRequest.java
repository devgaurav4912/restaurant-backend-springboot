package com.restaurant_backend.restaurant_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String password;
}

