package com.HealthCare.SystemApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Doctor.class, property = "doctorId")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorFristName;
    private String doctorLastName;
    private String doctorEmail;
    private String specialization;
    private Date inTime;
    private Date outTime;

    public Doctor(User user) {
        this.doctorFristName = user.getFirstname();
        this.doctorLastName = user.getLastname();
        this.doctorEmail = user.getEmail();
    }

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

}