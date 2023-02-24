package com.clinic.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clinic.model.Perfil;
import com.clinic.model.User;

public class userDetails implements UserDetails{

    private int id;
    private String login;
    private String senha;
    private Collection<? extends GrantedAuthority> perfis;

    public userDetails(User usuario, Set<Perfil> perfis){
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getPassword();
        this.perfis = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getROLE())).collect(Collectors.toList());
    }

    public int getId(){
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
