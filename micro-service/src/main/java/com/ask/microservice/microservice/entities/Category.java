package com.ask.microservice.microservice.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Category {
    @Id
    private  String id;
    private  String  name;
    
    @DBRef @ToString.Exclude
    private  Collection<Product> products = new ArrayList<>();
}