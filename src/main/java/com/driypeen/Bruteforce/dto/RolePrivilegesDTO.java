package com.driypeen.Bruteforce.dto;

import com.driypeen.Bruteforce.exception.DTOMappingException;
import com.driypeen.Bruteforce.model.Privilege;
import com.driypeen.Bruteforce.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Data
public class RolePrivilegesDTO {
    @JsonProperty("role")
    private String role;
    @JsonProperty("privileges")
    private Set<String> privileges;

    public static RolePrivilegesDTO mapToRolePrivilegesDTO(Role role) {
        try {
            RolePrivilegesDTO rolePrivilegesDTO = new RolePrivilegesDTO();
            rolePrivilegesDTO.role = role.getName();
            rolePrivilegesDTO.privileges = role
                    .getPrivileges()
                    .stream().map(Privilege::getName).collect(Collectors.toSet());

            return rolePrivilegesDTO;
        } catch (Exception e) {
            log.error("Error in mapToRolePrivilegesDTO");
            throw new DTOMappingException("Error in converted role privileges");
        }
    }
}
