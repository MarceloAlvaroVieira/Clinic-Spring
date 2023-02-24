package com.clinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * URLs that can be access without validation
     */
    private static final String[] PUBLIC_MATCHERS  ={
        "/login"
    };
    
    /**
     *  URLs that need USER validation
     */
    private static final String[] USER_MATCHERS ={
        "/patient/**",
        "/patient/",
        "/ficha/"
    };

    /**
     *  URLs that need ADMIN validation
     */
    private static final String[] ADMIN_MATCHERS ={
        "/user/"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();//disable cors

        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

        http.authorizeRequests()
        .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS).permitAll()
        .antMatchers(USER_MATCHERS).hasAnyRole("ROLE_USER", "ROLE_ADMIN", "USER", "ADMIN")
        .antMatchers(ADMIN_MATCHERS).hasAnyRole("ROLE_ADMIN", "ADMIN")
        .anyRequest().authenticated();
    } 

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}