package ru.kata.spring.boot_security.demo.controllers;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/users/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin/create-user";
    }

    @PostMapping("/users/create")
    public String createUser(@ModelAttribute User user,
                             @RequestParam(required = false) Long[] roleIds) {
        if (roleIds != null) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : roleIds) {
                Role role = roleService.findById(roleId);
                if (role != null) {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAll());
        return "admin/edit-user";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute User user,
                             @RequestParam(required = false) Long[] roleIds) {
        if (roleIds != null) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : roleIds) {
                Role role = roleService.findById(roleId);
                if (role != null) {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
        }
        user.setId(id);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}