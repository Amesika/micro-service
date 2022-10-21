package com.ask.microservice.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor @NoArgsConstructor @Data
@Getter
public class Login {
    private String username;
    private String password;
    private String repassword;
}
