package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }
}
