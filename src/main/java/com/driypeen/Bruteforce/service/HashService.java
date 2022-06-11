package com.driypeen.Bruteforce.service;

import com.driypeen.Bruteforce.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface HashService {
    String getHash(Message message);
}
