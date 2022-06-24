package com.driypeen.Bruteforce.controller;

import com.driypeen.Bruteforce.dto.RolePrivilegesDTO;
import com.driypeen.Bruteforce.dto.UserDTO;
import com.driypeen.Bruteforce.model.User;
import com.driypeen.Bruteforce.service.UserService;
import com.driypeen.Bruteforce.service.implementation.RoleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    @Lazy
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Lazy
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        log.info("GET ALL USERS");
        return ResponseEntity
                .ok()
                .body(userService.getAllUser());
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserByLogin(@RequestParam("login")String login) {
        log.info("GET INFO ABOUT: {}", login);
        return ResponseEntity.ok(userService.getUser(login));
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> newUser(@RequestBody RegistrationForm form) {
        User user = new User();
        user.setLogin(form.login);
        user.setPassword(form.password);

        log.info("NEW USER: {}", user.getLogin());
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/user/update-role")
    public ResponseEntity<?> updateUser(@RequestBody RoleToUserForm form) {
        log.info("UPDATE ROLE FOR USER {} TO {}", form.getLogin(), form.getRole());
        userService.updateRole(form.getLogin(), form.getRole());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<RolePrivilegesDTO> getRolePrivileges(@PathVariable("role") String role) {
        log.info("GET PRIVILEGES OF ROLE {}", role);
        return ResponseEntity.ok(roleService.getPrivilegesByName(role));
    }

    @Data
    private static final class RoleToUserForm {
        private String login;
        private String role;
    }

    @Data
    private static final class RegistrationForm {
        private String login;
        private String password;
    }
}
