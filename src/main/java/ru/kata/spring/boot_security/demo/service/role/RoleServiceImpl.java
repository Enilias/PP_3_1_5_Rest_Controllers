package ru.kata.spring.boot_security.demo.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRole(int id) {
        return roleRepository.getById(id);
    }

    public Collection<Role> getCollectionsRoles(String[] rolesName) {
       return roleRepository.findAllByNameIn(List.of(rolesName));
    }
}
