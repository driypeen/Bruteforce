package com.driypeen.Bruteforce.controller;

import com.driypeen.Bruteforce.dto.RolePrivilegesDTO;
import com.driypeen.Bruteforce.dto.UserDTO;
import com.driypeen.Bruteforce.model.User;
import com.driypeen.Bruteforce.service.UserService;
import com.driypeen.Bruteforce.service.implementation.RoleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        log.info("get all users");
        return ResponseEntity
                .ok()
                .body(userService.getAllUser());
    }

    @GetMapping("/user/{login}")
    public ResponseEntity<UserDTO> getUserByLogin(@PathVariable("login")String login) {
        log.info("get info about {}", login);
        return ResponseEntity.ok(userService.getUser(login));
    }

    @GetMapping("/user/new")
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/user/update-role/")
    public ResponseEntity<?> updateUser(@RequestBody RoleToUserForm form) {
        log.info("Update role for user {} to {}", form.getLogin(), form.getRole());
        userService.updateRole(form.getLogin(), form.getRole());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<RolePrivilegesDTO> getRolePrivileges(@PathVariable("role") String role) {
        log.info("Get {} privileges", role);
        return ResponseEntity.ok(roleService.getPrivilegesByName(role));
    }

    @Data
    private static final class RoleToUserForm {
        private String login;
        private String role;
    }
}
