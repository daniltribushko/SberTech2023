package com.example.sbertech2023.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthSecurityHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        boolean isAdmin = false;
        for (GrantedAuthority role : authentication.getAuthorities()){
            if (role.getAuthority().equals("ROLE_ADMIN")){
                isAdmin = true;
                break;
            }
        }
        if (isAdmin){
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("");
        }
    }
}
