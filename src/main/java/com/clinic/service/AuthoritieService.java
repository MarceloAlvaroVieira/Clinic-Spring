package com.clinic.service;

import org.springframework.stereotype.Service;

@Service
public class AuthoritieService {
    public static String getAuthorities(String authorities) {
        authorities = authorities.substring(1, authorities.length() -1);
        String[] auths = authorities.split(", ");

        String role= "";

        for (String auth : auths) {
            if(auth.contains("ROLE_CREATED")){
                role = "NEW_USER";
                break;
            }else if(auth.contains("ROLE_ADMIN")){
                role = "ADMIN";
            }else{
                role = "USER";
            }
        }
        return role;
    }
}
