package com.driypeen.users.service.implementation;

import com.driypeen.users.model.Role;
import com.driypeen.users.model.User;
import com.driypeen.users.repository.RoleRepository;
import com.driypeen.users.repository.UserRepository;
import com.driypeen.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateRole(String login, String roleName) {
        User user = userRepository.findByLogin(login);
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);
    }
}
