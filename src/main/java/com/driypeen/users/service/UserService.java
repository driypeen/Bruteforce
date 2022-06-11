package com.driypeen.users.service;

import com.driypeen.users.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(String login);
    List<User> getAllUser();
    void updateRole(String login, String roleName);
}
