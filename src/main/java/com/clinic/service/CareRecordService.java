package com.clinic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clinic.dto.CareRecordDTO;
import com.clinic.exception.ResourceNotFoundException;
import com.clinic.model.CareRecord;
import com.clinic.repository.CareRecordRepository;

@Service
public class CareRecordService{

    @Autowired
    CareRecordRepository repository;

    public ResponseEntity<CareRecordDTO> create(CareRecordDTO careRecordDTO) {
        
        CareRecord careRecord = repository.save(careRecordDTO.toCareRecord()); 

        return ResponseEntity.ok().body(new CareRecordDTO(careRecord));
    }

    public ResponseEntity<List<CareRecordDTO>> read() {
        
        List<CareRecord> records = repository.findAll();
        List<CareRecordDTO> recordsDTO = new ArrayList<>();

        records.forEach(record -> {
            recordsDTO.add(new CareRecordDTO(record));
        });

        return ResponseEntity.ok().body(recordsDTO);
    }

    public ResponseEntity<CareRecordDTO> getById(int id) {
        
        CareRecord record = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found!"));

        return ResponseEntity.ok().body(new CareRecordDTO(record));
    }

    public ResponseEntity<CareRecordDTO> update(int id, CareRecordDTO careRecordDTO) {
        
        CareRecord careRecord = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found!"));

        careRecord = careRecordDTO.toCareRecord();

        CareRecord record = repository.save(careRecord);

        return ResponseEntity.ok().body(new CareRecordDTO(record));
    }

    public ResponseEntity<Map<String, Boolean>> delete(int id) {

        CareRecord record = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found!"));
        
        repository.delete(record);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

}