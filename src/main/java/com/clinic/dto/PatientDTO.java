package com.clinic.dto;

import com.clinic.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("null")
public class PatientDTO {
    private int id;
    private String name;
    private String zip_code;
    private String address;
    private String locality;
    private String state;
    private String born;

    public PatientDTO(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.zip_code = patient.getZip_code();
        this.address = patient.getAddress();
        this.locality = patient.getLocality();
        this.state = patient.getState();
        this.born = patient.getBorn();
    }

    public Patient toPatient(){
        return new Patient(
            this.id,
            this.name,
            this.zip_code,
            this.address,
            this.locality,
            this.state,
            this.born
        );
    }
}
