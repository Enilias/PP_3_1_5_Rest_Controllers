package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import java.util.Arrays;


@Controller
@RequiredArgsConstructor
public class AdminController {
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


    @PostMapping("/admin")
    public String creat(@ModelAttribute("user") User user, @RequestParam("rolesName") String[] rolesName) {
        user.setRoles(roleService.getCollectionsRoles(rolesName));
        userService.save(user);
        return "redirect:admin";
    }


    @PatchMapping("/admin")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "rolesName", required = false) String[] rolesName) {
        System.out.println(user);
        System.out.println(Arrays.toString(rolesName));
        userService.update(user, roleService.getCollectionsRoles(rolesName));
        return "redirect:admin";
    }

    @DeleteMapping("/admin")
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:admin";
    }


}

