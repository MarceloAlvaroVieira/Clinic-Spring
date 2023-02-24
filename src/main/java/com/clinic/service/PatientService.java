package com.clinic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clinic.dto.PatientDTO;
import com.clinic.exception.ResourceNotFoundException;
import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;

    public ResponseEntity<PatientDTO> create(PatientDTO patientDTO) {

        Patient patient = repository.save(patientDTO.toPatient());

        return ResponseEntity.ok().body(new PatientDTO(patient));
    }

    public ResponseEntity<List<PatientDTO>> read() {
        
        List<Patient> patients = repository.findAll();
        List<PatientDTO> patientsDTO = new ArrayList<>();

        patients.forEach(patient ->{
            patientsDTO.add(new PatientDTO(patient));
        });

        return ResponseEntity.ok().body(patientsDTO);
    }

    public ResponseEntity<List<PatientDTO>> findByName(String name) {
        
        List<Patient> patients = null;
        List<PatientDTO> patientsDTO = new ArrayList<>();

        if(!name.isEmpty()){
            patients = repository.findByName(name);

            patients.forEach(patient ->{
                patientsDTO.add(new PatientDTO(patient));
            });
        }

        return ResponseEntity.ok().body(patientsDTO);
    }

    public ResponseEntity<PatientDTO> getById(int id) {
        
        Patient patient =  repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        return ResponseEntity.ok().body(new PatientDTO(patient));
    }

    public ResponseEntity<PatientDTO> update(int id, PatientDTO patientDTO) {
        
        Patient db_patient = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        db_patient = patientDTO.toPatient();

        Patient patient = repository.save(db_patient);

        return ResponseEntity.ok().body(new PatientDTO(patient));
    }

    public ResponseEntity<Map<String, Boolean>> delete(int id) {
         
        Patient patient = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        repository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }


    
}
