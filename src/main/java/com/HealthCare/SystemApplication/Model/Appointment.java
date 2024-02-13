package com.HealthCare.SystemApplication.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Appointment.class, property = "appointmentId")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private Date time;

    private String doctorName;

    private String patientName;

    @ManyToOne
    @JoinColumn(name = "doctorId") // add foreign key column with primary key column in Doctor
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    private String appointmentStatus = "Not Visited";
}