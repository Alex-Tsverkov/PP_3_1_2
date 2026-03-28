package ru.kata.spring.boot_security.demo.configs;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleService.save(adminRole);
            System.out.println("Created role: ROLE_ADMIN");
        }

        Role userRole = roleService.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleService.save(userRole);
            System.out.println("Created role: ROLE_USER");
        }

        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@example.com");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setAge(30);

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userService.save(admin);
            System.out.println("Created admin user: admin/admin123");
        }

        if (!userService.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setEmail("user@example.com");
            user.setFirstName("Regular");
            user.setLastName("User");
            user.setAge(25);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            user.setRoles(userRoles);

            userService.save(user);
            System.out.println("Created regular user: user/user123");
        }

        System.out.println("=== Data initialization completed ===");
    }
}
