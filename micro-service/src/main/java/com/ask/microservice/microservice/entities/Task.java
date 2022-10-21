package com.ask.microservice.microservice.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data  @AllArgsConstructor @NoArgsConstructor @ToString
public class Task {
    @Id @GeneratedValue
    private Long id;
    private String taskname;
}
