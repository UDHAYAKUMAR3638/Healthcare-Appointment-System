package com.HealthCare.SystemApplication.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.HealthCare.SystemApplication.model.Doctor;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DoctorOut {
    @Id
    private Long doctorId;
    private String doctorFristName;
    private String doctorLastName;
    private String doctorEmail;
    private String specialization;
    private Date inTime;
    private Date outTime;

    public DoctorOut(Doctor doctor) {
        this.doctorId = doctor.getDoctorId();
        this.doctorFristName = doctor.getDoctorFristName();
        this.doctorLastName = doctor.getDoctorLastName();
        this.specialization = doctor.getSpecialization();
        this.doctorEmail = doctor.getDoctorEmail();
        this.inTime=doctor.getInTime();
        this.outTime=doctor.getOutTime();
    }

    public static List<DoctorOut> fromDoctors(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorOut::new)
                .collect(Collectors.toList());
    }

}