package com.ask.microservice.microservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ask.microservice.microservice.entities.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
    
}
