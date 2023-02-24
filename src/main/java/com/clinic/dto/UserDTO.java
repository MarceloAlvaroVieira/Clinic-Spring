package com.clinic.dto;

import java.util.HashSet;
import java.util.Set;

import com.clinic.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("null")
public class UserDTO {
    private int id;
    private String name;
    private String login;
    private Set<Integer> perfis = new HashSet<>();
    private String password;
    private boolean type; 

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.perfis = user.getPerfisAsINT();
        this.password = user.getPassword();
        this.type = user.getType();
    }

    public User toUser(){
        return new User(
            this.id,
            this.name,
            this.login,
            this.perfis,
            this.password,
            this.type
        );
    }
}
