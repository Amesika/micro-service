package com.ask.microservice.auth.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ask.microservice.auth.dao.AppRoleRepository;
import com.ask.microservice.auth.dao.AppUserRepository;
import com.ask.microservice.auth.entities.AppRole;
import com.ask.microservice.auth.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bcpe;

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
        AppUser user = appUserRepository.findByUsername(username);

        if (user != null)
            throw new RuntimeException("User already exists.");
        if (!password.equals(confirmedPassword))
            throw new RuntimeException("Please confirme your password.");

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bcpe.encode(password));
        appUser.setActived(true);

        appUserRepository.save(appUser);
        addRoleToUser(username, "USER");

        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRole(rolename);
        user.getRoles().add(role);
    }
}
