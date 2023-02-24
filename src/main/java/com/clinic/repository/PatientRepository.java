package com.clinic.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    
    @Query("from Patient where name like %?1%")
    ArrayList<Patient> findByName(String name);   

}
