package com.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "care_record")
public class CareRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
 
    @NonNull
    @Column(name = "forwarding")
    private String forwarding;

    @NonNull
    @Column(name = "used_material")
    private String used_material;

    @NonNull
    @Column(name = "code")
    private String code;

    @NonNull
    @Column(name = "procedure")
    private String procedure;

    @NonNull
    @Column(name = "cid")
    private String cid;

    @NonNull
    @Column(name = "diagnosis")
    private String diagnosis;

    @NonNull
    @Column(name = "info_attendance")
    private String info_attendance;

    @NonNull
    @Column(name = "arrival_time")
    private String arrival_time;

    @NonNull
    @Column(name = "consultation_date")
    private String consultation_date;

}
