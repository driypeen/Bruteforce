package com.driypeen.Bruteforce.controller;

import com.driypeen.Bruteforce.model.Message;
import com.driypeen.Bruteforce.service.HashService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {
    private final HashService hashService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    public MessageController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping("/decode")
    String decodeText(@RequestBody Message message) {
        LOGGER.info("Get request: {}", message);
        return hashService.getHash(message);
    }
}
