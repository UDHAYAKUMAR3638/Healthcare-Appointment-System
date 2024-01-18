package com.divya.inventorymanagement.Model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DoctorOut {
    @Id
    private Long doctorId;
    private String doctorName;
    private String specialization;

    public DoctorOut(Doctor doctor) {
        this.doctorId = doctor.getDoctorId();
        this.doctorName = doctor.getDoctorName();
        this.specialization = doctor.getSpecialization();
    }

    public static List<DoctorOut> fromDoctors(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorOut::new)
                .collect(Collectors.toList());
    }

}