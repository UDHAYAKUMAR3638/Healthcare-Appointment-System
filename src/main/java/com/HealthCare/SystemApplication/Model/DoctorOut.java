package com.HealthCare.SystemApplication.Model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class DoctorOut {
    @Id
    private Long doctorId;
    private String doctorFristName;
    private String doctorLastName;
    private String doctorEmail;
    private String specialization;

    public DoctorOut(Doctor doctor) {
        this.doctorId = doctor.getDoctorId();
        this.doctorFristName = doctor.getDoctorFristName();
        this.doctorLastName = doctor.getDoctorLastName();
        this.specialization = doctor.getSpecialization();
        this.doctorEmail = doctor.getDoctorEmail();

    }

    public static List<DoctorOut> fromDoctors(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorOut::new)
                .collect(Collectors.toList());
    }

}