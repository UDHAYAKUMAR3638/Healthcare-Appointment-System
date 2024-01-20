package com.HealthCare.SystemApplication.Model;

import com.HealthCare.SystemApplication.Users.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Doctor(User user) {
        this.doctorFristName = user.getFirstname();
        this.doctorLastName = user.getLastname();
        this.doctorEmail = user.getEmail();
    }

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

}