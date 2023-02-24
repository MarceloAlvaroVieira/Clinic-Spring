package com.clinic.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private String login;
    private String password;
}
