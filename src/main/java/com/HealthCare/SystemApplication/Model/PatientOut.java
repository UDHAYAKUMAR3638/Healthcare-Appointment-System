package com.HealthCare.SystemApplication.Model;

import java.util.List;
import java.util.stream.Collectors;
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
        this.patientId = patient.patientId;
        this.patientFirstName = patient.patientFirstName;
        this.patientLastName = patient.patientLastName;
        this.patientEmail = patient.patientEmail;
        this.patientContact = patient.patientContact;
    }

    public static List<PatientOut> fromPatients(List<Patient> patients) {
        return patients.stream()
                .map(PatientOut::new)
                .collect(Collectors.toList());
    }

}
