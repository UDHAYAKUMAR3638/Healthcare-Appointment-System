package com.HealthCare.SystemApplication.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.repository.PatientRepo;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.service.DoctorService;
import com.HealthCare.SystemApplication.service.PatientService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentOut {
    @Autowired
    AppointmentService appointmentService;
    @Id
    public Long appointmentId;
    public Date time;
    public Long doctorId;
    public Long patientId;
    public String doctorName;
    public String patientName;
    public String appointmentStatus = "Not Visited";

    public AppointmentOut(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.time = appointment.getTime();
        this.doctorId = appointment.getDoctor().getDoctorId();
        this.patientId = appointment.getPatient().getPatientId();
        this.appointmentStatus = appointment.getAppointmentStatus();
        this.patientName = appointment.getPatientName();
        this.doctorName = appointment.getDoctorName();
    }

    public static List<AppointmentOut> fromAppointments(List<Appointment> appointment) {
        return appointment.stream()
                .map(AppointmentOut::new)
                .collect(Collectors.toList());
    }
}
