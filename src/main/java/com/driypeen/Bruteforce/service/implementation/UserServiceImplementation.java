package com.driypeen.Bruteforce.service.implementation;

import com.driypeen.Bruteforce.dto.UserDTO;
import com.driypeen.Bruteforce.exception.NotFoundElementException;
import com.driypeen.Bruteforce.model.Role;
import com.driypeen.Bruteforce.model.User;
import com.driypeen.Bruteforce.repository.RoleRepository;
import com.driypeen.Bruteforce.repository.UserRepository;
import com.driypeen.Bruteforce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO saveUser(User user) {
        return UserDTO.mapToUserDTO(userRepository.save(user));
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
