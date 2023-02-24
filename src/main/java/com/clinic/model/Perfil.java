package com.clinic.model;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    CREATED(3, "ROLE_CREATED");

    Integer ID;
    String ROLE;

    Perfil(Integer ID, String ROLE){
        this.ID = ID;
        this.ROLE = ROLE;
    }

	public static Perfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getID())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid Id: " + cod);
	}

    public String getROLE() {
        return this.ROLE;
    }


}
