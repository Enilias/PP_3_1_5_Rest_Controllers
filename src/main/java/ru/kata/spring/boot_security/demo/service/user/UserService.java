package ru.kata.spring.boot_security.demo.service.user;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    void save(User user);

    void delete(int id);

    User getUser(int id);

    void update(int id, String firstName, String lastName, byte age, String userName, String password, Collection<Role> roles);
}