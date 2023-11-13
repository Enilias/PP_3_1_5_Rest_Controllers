package ru.kata.spring.boot_security.demo.dao.role;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoles();

    void save(Role role);

    void delete(int id);

    Role getRole(int id);
}
