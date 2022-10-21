package com.ask.microservice.auth.services;

import com.ask.microservice.auth.entities.AppRole;
import com.ask.microservice.auth.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(String username, String password, String confirmedPassword);

    public AppRole saveRole(AppRole role);

    public AppUser loadUserByUsername(String username);

    public void addRoleToUser(String username, String rolename);
}
