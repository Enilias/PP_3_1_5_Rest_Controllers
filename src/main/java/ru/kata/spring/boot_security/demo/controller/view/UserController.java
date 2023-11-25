package ru.kata.spring.boot_security.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user")
    public String userInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("userinfo", authentication.getPrincipal());
        return "user/user";
    }
}
