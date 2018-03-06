/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import io.muic.ooc.webapp.database.MySQL;
import io.muic.ooc.webapp.database.User;
import org.apache.commons.lang.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
//    private Map<String, String> userCredentials = new HashMap<String, String>() {{
//        put("admin", "123456");
//        put("muic", "ooc");
//    }};
    
    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null &&MySQL.getUser(username)!=null );
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        User user = MySQL.getUser(username);
        if(user==null) return false;
        if (BCrypt.checkpw(password, user.getPassword())) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }
    
//    public void logout(HttpServletRequest request) {
//        request.getSession().invalidate();
//    }
    
}
