package com.driypeen.Bruteforce.dto;

import com.driypeen.Bruteforce.exception.DTOMappingException;
import com.driypeen.Bruteforce.model.Privilege;
import com.driypeen.Bruteforce.model.Role;
import com.driypeen.Bruteforce.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Data
@JsonRootName(value = "user")
public class UserDTO {
    @JsonProperty("login")
    private String login;
    @JsonProperty("role")
    private String role;
    @JsonProperty("privileges")
    private Set<String> privileges;

    public static UserDTO mapToUserDTO(User user) {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.login = user.getLogin();
            userDTO.role = user.getRole().getName();
            userDTO.privileges = user
                    .getRole()
                    .getPrivileges()
                    .stream()
                    .map(Privilege::getName).collect(Collectors.toSet());
            return userDTO;
        } catch (Exception e) {
            log.error("Error in mapToListUserDTO");
            throw new DTOMappingException("Error in converted users");
        }
    }

    public static List<UserDTO> mapToListUserDTO(List<User> users) {
        try {
            return users
                    .stream()
                    .map(UserDTO::mapToUserDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error in mapToListUserDTO");
            throw new DTOMappingException("Error in converted users");
        }
    }
}
