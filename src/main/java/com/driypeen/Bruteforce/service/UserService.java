package com.driypeen.Bruteforce.service;

import com.driypeen.Bruteforce.dto.UserDTO;
import com.driypeen.Bruteforce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO saveUser(User user);
    UserDTO getUser(String login);
    List<UserDTO> getAllUser();
    void updateRole(String login, String roleName);
}
