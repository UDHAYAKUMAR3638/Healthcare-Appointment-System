package com.HealthCare.SystemApplication.Model;

import java.time.LocalDateTime;

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
    public Long appointmentId;

    public LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "doctor_id") // add foreign key column with primary key column in Doctor
    public Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;
}