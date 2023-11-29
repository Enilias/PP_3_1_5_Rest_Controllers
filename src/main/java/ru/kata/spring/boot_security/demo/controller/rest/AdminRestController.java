package ru.kata.spring.boot_security.demo.controller.rest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @PostMapping("/admin/create")
    public User create(@ModelAttribute("user") User user, @RequestParam(value = "rolesName", required = false) String[] rolesName) {
        userService.save(user, roleService.getCollectionsRoles(rolesName));
        return userService.getUser(user.getId());
    }


    @PatchMapping("/admin/update")
    public User update(@ModelAttribute("user") User user, @RequestParam(value = "rolesName", required = false) String[] rolesName) {
        userService.update(user, roleService.getCollectionsRoles(rolesName));
        return userService.getUser(user.getId());
    }

    @DeleteMapping("/admin/delete")
    public void delete(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
    }
}
