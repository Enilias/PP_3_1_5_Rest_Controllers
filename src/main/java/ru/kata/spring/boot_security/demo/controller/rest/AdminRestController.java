package ru.kata.spring.boot_security.demo.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/admin/users")
    public List<User> getListUser() {
        return userService.getUsers();
    }

    @GetMapping("/admin/role")
    public List<Role> getListRole() {
        return roleService.getRoles();
    }

    @GetMapping("/admin/authentication")
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @PostMapping("/admin/new")
    public void creat(@RequestBody User user) {
        userService.save(user);
    }


    @PatchMapping("/admin/update")
    public void update(@RequestBody User user) {
        userService.update(user, user.getRoles());
    }

    @DeleteMapping("/admin/delete")
    public void delete(@RequestParam("id") int id) {
        userService.delete(id);
    }
}
