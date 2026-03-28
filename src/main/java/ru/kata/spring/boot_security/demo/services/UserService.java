package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}