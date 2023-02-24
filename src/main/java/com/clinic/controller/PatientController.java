package com.clinic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.PatientDTO;
import com.clinic.service.PatientService;

@RestController
@RequestMapping("/patient")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController extends Exception {

    @Autowired
    PatientService service;
    
    @PostMapping()
    public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO patientDTO){
        return service.create(patientDTO);
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> read() {
        return service.read();
    }

    @GetMapping(value="/find/{name}")
    public ResponseEntity<List<PatientDTO>> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable int id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable int id, @RequestBody PatientDTO patientDTO) {
        return service.update(id, patientDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable int id){
        return service.delete(id);
    }    
}