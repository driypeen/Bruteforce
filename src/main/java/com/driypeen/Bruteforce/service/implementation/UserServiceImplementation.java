package com.driypeen.Bruteforce.service.implementation;

import com.driypeen.Bruteforce.dto.UserDTO;
import com.driypeen.Bruteforce.exception.NotFoundElementException;
import com.driypeen.Bruteforce.model.Role;
import com.driypeen.Bruteforce.model.User;
import com.driypeen.Bruteforce.repository.RoleRepository;
import com.driypeen.Bruteforce.repository.UserRepository;
import com.driypeen.Bruteforce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImplementation implements UserService, UserDetailsService {
    private static final String DEFAULT_ROLE = "ROLE_DEFAULT";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> u = userRepository.findByLogin(login);
        User user = u.orElseThrow(() -> {
            log.info("User {} not found", login);
            return new UsernameNotFoundException("User not found");
        });

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

    @Override
    public UserDTO saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        Optional<Role> defaultRole = roleRepository.findByName(DEFAULT_ROLE);
        Role role = defaultRole.orElseThrow(() -> new NotFoundElementException("CANT FIND ROLE WITH NAME " + DEFAULT_ROLE));

        user.setRole(role);
        userRepository.save(user);
        log.info("USER WAS SUCCESSFULLY SAVED {}", user.getLogin());
        return UserDTO.mapToUserDTO(user);
    }

    @Override
    public UserDTO getUser(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.map(UserDTO::mapToUserDTO).orElseThrow(() -> new NotFoundElementException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return UserDTO.mapToListUserDTO(userRepository.findAll());
    }

    @Override
    public void updateRole(String login, String roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        userRepository
                .findByLogin(login)
                .ifPresent(current ->
                        current.setRole(
                                role.orElseThrow(() -> new NotFoundElementException("Role nor found"))));
    }
}
