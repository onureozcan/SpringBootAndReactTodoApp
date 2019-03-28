package org.zero.todoapp.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashingUtils {

    public static String hashUserPassword(String password) throws NoSuchAlgorithmException {
        // TODO: MD5 is not enough for such job
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

}
