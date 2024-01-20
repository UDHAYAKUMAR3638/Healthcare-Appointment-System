package com.HealthCare.SystemApplication.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.HealthCare.SystemApplication.model.Patient;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PatientOut {
    @Id
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientEmail;
    private String patientContact;

    public PatientOut(Patient patient) {
        this.patientId = patient.getPatientId();
        this.patientFirstName = patient.getPatientFirstName();
        this.patientLastName = patient.getPatientLastName();
        this.patientEmail = patient.getPatientEmail();
        this.patientContact = patient.getPatientContact();
    }

    public static List<PatientOut> fromPatients(List<Patient> patients) {
        return patients.stream()
                .map(PatientOut::new)
                .collect(Collectors.toList());
    }

}
