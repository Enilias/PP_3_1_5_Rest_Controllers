package ru.kata.spring.boot_security.demo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Data
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Column
    @NotEmpty(message = "Sur-Name should not be empty")
    private String surname;
    @Column(name = "password")
    private String password;
    @ManyToMany
    private Collection<Role> roles;

    public User() {

    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

}