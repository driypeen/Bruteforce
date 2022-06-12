package com.driypeen.Bruteforce.service.implementation;

import com.driypeen.Bruteforce.dto.RolePrivilegesDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RolePrivilegesDTO getPrivilegesByName(String name);
}
