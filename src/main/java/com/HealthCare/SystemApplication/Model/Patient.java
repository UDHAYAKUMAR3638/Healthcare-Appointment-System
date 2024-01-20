package com.HealthCare.SystemApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    @Column(nullable = false, unique = true)
    private String patientEmail;
    private String patientContact;

    public Patient(String patientFirstName, String patientLastName, String patientEmail, String patientContact) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientContact = patientContact;
    }

    public Patient(User user) {
        this.patientFirstName = user.getFirstname();
        this.patientLastName = user.getLastname();
        this.patientEmail = user.getEmail();
    }

    @OneToOne(mappedBy = "patient")
    public Appointment appointment;
}
