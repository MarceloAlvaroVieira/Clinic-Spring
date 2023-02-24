package com.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Lazy(value = true)
    @NonNull
    @Column(name = "zip_code")
    private String zip_code;
    
    @Lazy(value = true)
    @NonNull
    @Column(name = "address")
    private String address;
    
    @Lazy(value = true)
    @NonNull
    @Column(name = "locality")
    private String locality;

    @Lazy(value = true)
    @NonNull
    @Column(name = "state")
    private String state;

    @Lazy(value = true)
    @NonNull
    @Column(name = "born")
    private String born;
}
