package com.clinic.dto;

import com.clinic.model.CareRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("null")
public class CareRecordDTO {
    private int id;
    private PatientDTO patient;
    private String forwarding;
    private String used_material;
    private String code;
    private String procedure;
    private String cid;
    private String diagnosis;
    private String info_attendance;
    private String arrival_time;
    private String consultation_date;

    public CareRecordDTO(CareRecord form){
        this.id = form.getId();
        this.patient = new PatientDTO(form.getPatient());
        this.forwarding = form.getForwarding();
        this.used_material = form.getUsed_material();
        this.code = form.getCode();
        this.procedure = form.getProcedure();
        this.cid = form.getCid();
        this.diagnosis = form.getDiagnosis();
        this.info_attendance = form.getInfo_attendance();
        this.arrival_time = form.getArrival_time();
        this.consultation_date = form.getConsultation_date();
    }

    public CareRecord toCareRecord(){
        return new CareRecord(
            this.id,
            this.patient.toPatient(),
            this.forwarding,
            this.used_material,
            this.code,
            this.procedure,
            this.cid,
            this.diagnosis,
            this.info_attendance,
            this.arrival_time,
            this.consultation_date
        );
    }
}
