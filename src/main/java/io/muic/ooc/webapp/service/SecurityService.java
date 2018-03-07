/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
//    private Map<String, String> userCredentials = new HashMap<String, String>() {{
//        put("admin", "123456");
//        put("muic", "1111");
//    }};
    private Set<String> userCredentials = new HashSet<String>(){{

    }};
    
    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
//       return (username != null && userCredentials.contains(username));
        return username != null;
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        //String passwordInDB = userCredentials.get(username);
        //password = Encryption.encryptPassword(password);
        String[] userNP = DatabaseUtil.getUsername(username,password);
        System.out.println(password);
        if(userNP != null){
            boolean isMatched = username.equals(userNP[0])&&Encryption.verifyPassword(password,userNP[1]);
            if (isMatched) {
                request.getSession().setAttribute("username", username);
                userCredentials.add(username);
                return true;
            }
        }
        return false;
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}
