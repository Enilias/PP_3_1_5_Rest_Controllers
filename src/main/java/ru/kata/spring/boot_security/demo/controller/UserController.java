package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import javax.management.ConstructorParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userinfo", authentication.getPrincipal());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getRoles());
        return "admin/admin";
    }

//    @GetMapping("/admin/all")
//    public String getUsers(Model model) {
//        model.addAttribute("users", userService.getUsers());
//        return "admin/all";
//    }

//    @GetMapping("/admin")чисто
//    public void newUser(Model model) {
//        model.addAttribute("user", new User());
//    }

    @PostMapping("/admin")
    public String creat(@ModelAttribute("user") User user, @RequestParam("rolesName") String[] rolesName) {
        System.out.println(Arrays.toString(rolesName));
        user.setRoles(roleService.getCollectionsRoles(rolesName));
        userService.save(user);
        return "redirect:admin";
    }

//    @GetMapping("/admin")
//    public void getUpdate(@RequestParam("id") int id, Model model) {
//        model.addAttribute("user", userService.getUser(id));
//    }

    @PatchMapping("/admin")
    public String update(@ModelAttribute("user") User user,@RequestParam("rolesName") String[] rolesName) {
        userService.update(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getUsername(),
                user.getPassword(),
                roleService.getCollectionsRoles(rolesName));
        return "redirect:admin";
    }

    @DeleteMapping("/admin")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:all";
    }

//    @GetMapping("/admin/userInfo")
//    public void getUser(@RequestParam("id") int id, Model model) {
//        model.addAttribute("userInfo", userService.getUser(id));
//    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", authentication.getPrincipal());
        return "user/user";
    }

}

