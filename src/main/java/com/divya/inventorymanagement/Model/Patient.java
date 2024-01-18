package com.divya.inventorymanagement.Model;

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
    public Long patientId;
    public String patientFirstName;
    public String patientLastName;
    @Column(nullable = false, unique = true)
    public String patientEmail;
    @Column(nullable = false)
    public String patientPassword;
    public String patientContact;

    public Patient(String patientFirstName, String patientLastName, String patientEmail, String patientPassword,
            String patientContact) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
        this.patientContact = patientContact;
    }

    @OneToOne(mappedBy = "patient")
    public Appointment appointment;
}
