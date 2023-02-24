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

import com.clinic.dto.CareRecordDTO;
import com.clinic.service.CareRecordService;

@RestController
@RequestMapping("/record")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
public class CareRecordController {
    
    @Autowired
    CareRecordService service;

    @PostMapping()
    public ResponseEntity<CareRecordDTO> create(@RequestBody CareRecordDTO careRecordDTO){
        return service.create(careRecordDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CareRecordDTO>> read(){
        return service.read();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareRecordDTO> getById(@PathVariable int id){
        return service.getById(id);    
    }

    @PutMapping("controle/{id}")
    public ResponseEntity<CareRecordDTO> update(@PathVariable int id, @RequestBody CareRecordDTO careRecordDTO){
        return service.update(id, careRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable int id){
        return service.delete(id);
    }
}