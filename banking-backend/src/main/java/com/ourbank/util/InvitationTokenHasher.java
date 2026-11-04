package com.ourbank.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public final class InvitationTokenHasher {

    private InvitationTokenHasher(){}

    public static String hash(String token){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        }catch (NoSuchAlgorithmException exception){
            throw new IllegalArgumentException("SHA-256 algorithm is unavailable", exception);
        }
    }
}
