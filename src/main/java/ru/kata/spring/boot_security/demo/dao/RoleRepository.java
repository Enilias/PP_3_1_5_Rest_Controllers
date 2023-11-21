package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    void deleteById(int id);

    Collection<Role> findAllByNameIn(Collection<String> name);
}
