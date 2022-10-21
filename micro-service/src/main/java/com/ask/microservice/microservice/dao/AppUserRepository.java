package com.ask.microservice.microservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ask.microservice.microservice.entities.AppUser;

public interface AppUserRepository  extends JpaRepository<AppUser,Long>{
    public AppUser findByUsername(String username);
}
