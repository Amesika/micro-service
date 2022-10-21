package com.ask.microservice.microservice.service;

import com.ask.microservice.microservice.entities.AppRole;
import com.ask.microservice.microservice.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(AppUser u);
    public AppRole saveRole(AppRole r);
    public AppUser findUserByUsername(String username);
    public void addRoleToUser(String username, String role);
}

