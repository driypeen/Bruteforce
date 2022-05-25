package com.driypeen.Bruteforce.model;

import lombok.Data;

@Data
public class Message {
    private String hash;
    private int sourceTextLength;
    private String alphabet;
    private String algorithm;
}
