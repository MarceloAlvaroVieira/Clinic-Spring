package com.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.model.CareRecord;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecord, Integer> {
    
}
