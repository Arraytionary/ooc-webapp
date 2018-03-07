package io.muic.ooc.webapp.service;

import org.mindrot.jbcrypt.BCrypt;

public class Encryption {
    public static String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(4));
    }
    public static boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
