package com.clinic.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user")
@SuppressWarnings("null")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;
    
    @NonNull
    @Column(name = "login")
    private String login;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ROLES")
    private Set<Integer> perfis = new HashSet<>();

    @Column(name = "password")
    private String password;

    @Transient
    private boolean type;

    public User(){
        setPerfil(Perfil.CREATED);
    }

    public void setPerfil(Perfil perfil){
        perfis.add(perfil.getID());
    }

    public void removePerfil(Perfil perfil){
        try{
            perfis.remove(perfil.getID());
        }catch(Exception e){System.out.println("No ROLE_CREATED to remove!");}
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public Set<Integer> getPerfisAsINT(){
        return perfis;
    }

    public boolean getType() {
        return type;
    }
}