package com.driypeen.users.controller;

import com.driypeen.users.model.User;
import com.driypeen.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class UserController {
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser() {
        LOGGER.info("log all users");
        return userService.getAllUser();
    }
}
