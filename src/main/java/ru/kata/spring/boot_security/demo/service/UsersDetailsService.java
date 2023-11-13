package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.configs.security.UsersDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;
//Получение пользователя по имени
@Service
public class UsersDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //Опшинал с обьектом юзер
        Optional<User> userOptional = userRepository.findByName(s);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        //Оборачиваем в юсер делейл и возвращаем
        return new UsersDetails(userOptional.get());
    }
}
