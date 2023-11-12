package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "all";
    }

    @GetMapping("/new")
    public void newUser(Model model) {
        model.addAttribute("user", new User());
    }

    @PostMapping("/new")
    public String creat(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:all";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:all";
    }

    @GetMapping("/update")
    public void getUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user.getName(), user.getSurname());
        return "redirect:all";
    }

    @GetMapping("/user")
    public void getUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
    }

    @PostMapping("/user")
    public String user(@ModelAttribute User user) {
        userService.update(user.getId(), user.getName(), user.getSurname());
        return "redirect:user";
    }

}

