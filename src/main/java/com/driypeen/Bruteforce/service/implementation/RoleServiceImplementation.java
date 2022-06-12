package com.driypeen.Bruteforce.service.implementation;

import com.driypeen.Bruteforce.dto.RolePrivilegesDTO;
import com.driypeen.Bruteforce.exception.NotFoundElementException;
import com.driypeen.Bruteforce.model.Role;
import com.driypeen.Bruteforce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService{
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RolePrivilegesDTO getPrivilegesByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.map(RolePrivilegesDTO::mapToRolePrivilegesDTO)
                .orElseThrow(() -> new NotFoundElementException("Role not found"));
    }
}
