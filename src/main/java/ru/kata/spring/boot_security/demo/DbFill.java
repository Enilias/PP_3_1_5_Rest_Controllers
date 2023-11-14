package ru.kata.spring.boot_security.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RequiredArgsConstructor
@Component
public class DbFill {

    private final UserService userService;
    private final RoleService roleService;


    @PostConstruct
    private void postConstruct() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        roleService.save(userRole);
        roleService.save(adminRole);
        for (int i = 0; i < 3; i++) {
            userService.save(new User(
                    "John" + i,
                    "Smith" + i,
                    "username" + i,
                    "password" + i,
                    new HashSet<>(Collections.singleton(userRole)))
            );
        }
        userService.save(new User("admin",
                "admini4",
                "admin",
                "admin",
                new HashSet<>(Arrays.asList(adminRole, userRole))
        ));

    }
}
