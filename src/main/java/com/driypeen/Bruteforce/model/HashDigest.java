package com.driypeen.Bruteforce.model;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;

public class HashDigest {
    private final MessageDigest messageDigest;

    public HashDigest(String algorithm) {
        this.messageDigest = DigestUtils.getDigest(algorithm);
    }

    public Mono<String> getHash(String text) {
        return Mono.just(
                Hex.encodeHexString(
                        messageDigest.digest(
                                StringUtils.getBytesUtf8(text))));
    }
}
