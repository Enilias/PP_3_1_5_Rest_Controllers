package ru.kata.spring.boot_security.demo.controller.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @GetMapping("/user/authentication")
    public Authentication userInfo() {

        return SecurityContextHolder.getContext().getAuthentication();
    }
}
