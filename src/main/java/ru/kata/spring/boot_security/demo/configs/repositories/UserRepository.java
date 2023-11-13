package ru.kata.spring.boot_security.demo.configs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.configs.security.UsersDetails;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);
}
