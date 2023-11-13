package ru.kata.spring.boot_security.demo.service.role;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    void save(Role role);

    void delete(int id);

    Role getRole(int id);
}
