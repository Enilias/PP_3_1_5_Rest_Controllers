package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import javax.annotation.PostConstruct;

@Component
public class DbFill {

    private final UserService userService;

    @Autowired
    public DbFill(UserService userService) {
        this.userService = userService;
    }


    @PostConstruct
    private void postConstruct() {
        User admin = new User("A", "A");
        User user = new User("B", "B");
        admin.setPassword("111");
        user.setPassword("222");
        userService.save(admin);
        userService.save(user);


    }
}
