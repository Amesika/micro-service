package com.ask.microservice.microservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ask.microservice.microservice.dto.Login;
import com.ask.microservice.microservice.entities.AppUser;
import com.ask.microservice.microservice.service.AccountService;

public class UserControler {
    @Autowired
    private AccountService accountService;

    @PostMapping("/users")
    public AppUser signUp(@RequestBody Login data) {
        String username = data.getUsername();
        AppUser user = accountService.findUserByUsername(username);

        if (user != null)
            throw new RuntimeException("This user already exists, try with other username.");

        String password = data.getPassword();
        String repassowrd = data.getRepassword();

        if (!password.equals(repassowrd))
            throw new RuntimeException("You mut confirme your password.");

        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(password);
        accountService.saveUser(u);
        accountService.addRoleToUser(username, "USER");

        return u;
    }
}
