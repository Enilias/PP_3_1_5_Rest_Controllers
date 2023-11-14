package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.user.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/all")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/all";
    }

    @GetMapping("/admin/new")
    public void newUser(Model model) {
        model.addAttribute("user", new User());
    }

    @PostMapping("/admin/new")
    public String creat(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:all";
    }

    @DeleteMapping("/admin/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:all";
    }

    @GetMapping("/admin/update")
    public void getUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
    }

    @PatchMapping("/admin/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword());
        return "redirect:all";
    }

    @GetMapping("/admin/userInfo")
    public void getUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("userInfo", userService.getUser(id));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        return "user/user";
    }

}

