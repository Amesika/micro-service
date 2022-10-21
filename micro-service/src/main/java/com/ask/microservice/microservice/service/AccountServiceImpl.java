package com.ask.microservice.microservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ask.microservice.microservice.dao.AppRoleRepository;
import com.ask.microservice.microservice.dao.AppUserRepository;
import com.ask.microservice.microservice.entities.AppRole;
import com.ask.microservice.microservice.entities.AppUser;

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
    public AppUser saveUser(AppUser u) {
        u.setPassword(bcpe.encode(u.getPassword()));
        return appUserRepository.save(u);
    }

    @Override
    public AppRole saveRole(AppRole r) {
        return appRoleRepository.save(r);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByRole(rolename);
        user.getRoles().add(role);
    }
}
