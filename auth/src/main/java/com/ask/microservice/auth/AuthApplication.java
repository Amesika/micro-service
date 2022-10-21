package com.ask.microservice.auth;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ask.microservice.auth.entities.AppRole;
import com.ask.microservice.auth.services.AccountService;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.saveRole(new AppRole(null, "USER"));
			accountService.saveRole(new AppRole(null, "ADMIN"));

			Stream.of("user1", "user2", "user3", "user4", "admin").forEach(u -> {
				accountService.saveUser(u, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}

	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
