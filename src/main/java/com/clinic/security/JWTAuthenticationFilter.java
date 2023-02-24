package com.clinic.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.clinic.dto.CredentialsDTO;
import com.clinic.service.AuthoritieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;

@Setter
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    AuthenticationManager authenticationManager;
    JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            CredentialsDTO credenciais = new ObjectMapper()
                .readValue(request.getInputStream(), CredentialsDTO.class);

                System.out.println(credenciais.getLogin() + "\n" +  credenciais.getPassword()+ "\n");
                
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciais.getLogin(), credenciais.getPassword(), new ArrayList<>());
                
                Authentication auth = authenticationManager.authenticate(authToken);

                return auth;

        } catch (IOException e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
        
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
    Authentication auth) throws IOException, ServletException {
        
        String login = ((userDetails) auth.getPrincipal()).getUsername();
        String token = jwtUtil.tokenGenerator(login);
        UserDetails usuario = (userDetails)auth.getPrincipal();

        String authorities = String.valueOf(usuario.getAuthorities());
        String role = AuthoritieService.getAuthorities(authorities);

        if(usuario.getAuthorities() != null){
            response.addHeader("ROLE", role);
            response.addHeader("access-control-expose-headers", "ROLE");
        }
        
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }
    
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        org.springframework.security.core.AuthenticationException exception)
                throws IOException, ServletException {
                    response.setStatus(401);
                    response.setContentType("application/json"); 
                    response.getWriter().append(json());   
        }
    
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Not authorized\", "
                + "\"message\": \"Invalid login or password\", "
                + "\"path\": \"/login\"}";
        }
    }
}
