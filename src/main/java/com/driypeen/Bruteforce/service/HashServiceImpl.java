package com.driypeen.Bruteforce.service;

import com.driypeen.Bruteforce.model.Message;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class HashServiceImpl implements HashService{
    public String getHash(Message message) {
//        return Hex.encodeHexString(
//                MessageDigest.getInstance(message.getAlgorithm())
//                .digest
//                        (StringUtils.getBytesUtf8(message.getHash())));
        return "";
    }
}
