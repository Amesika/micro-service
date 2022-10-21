package com.ask.microservice.microservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ask.microservice.microservice.entities.AppRole;

public interface AppRoleRepository  extends JpaRepository<AppRole,Long>{
    public AppRole findByRole(String role);
}
