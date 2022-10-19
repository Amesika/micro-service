package com.ask.microservice.microservice;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ask.microservice.microservice.dao.CategoryRepository;
import com.ask.microservice.microservice.dao.ProductRepository;
import com.ask.microservice.microservice.entities.Category;
import com.ask.microservice.microservice.entities.Product;

@SpringBootApplication
public class MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args -> {
			categoryRepository.deleteAll();
			Stream.of("C1 Ordinateurs", "C2 Imprimantes").forEach(c -> {
				categoryRepository.save(new Category(c.split(" ")[0], c.split(" ")[1], new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);

			productRepository.deleteAll();
			Category c1= categoryRepository.findById("C1").get();
			Stream.of("P1","P2","P3","P4").forEach(c -> {
				Product p = productRepository.save(new Product(null, c,Math.random()*1000 ,c1));
				c1.getProducts().add(p);
				categoryRepository.save(c1);
			});
			Category c2= categoryRepository.findById("C2").get();
			Stream.of("P5","P6").forEach(c -> {
				Product p = productRepository.save(new Product(null, c,Math.random()*1000 ,c2));
				c2.getProducts().add(p);
				categoryRepository.save(c2);
			});
			productRepository.findAll().forEach(System.out::println);

		};
	}

}
