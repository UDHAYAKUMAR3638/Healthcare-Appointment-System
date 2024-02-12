package com.HealthCare.SystemApplication.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.HealthCare.SystemApplication.model.Appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentOut {
    @Id
    public Long appointmentId;
    public Date time;
    public Long doctorId;
    public Long patientId;
    public String appointmentStatus = "Not Visited";

    public AppointmentOut(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.time = appointment.getTime();
        this.doctorId = appointment.getDoctor().getDoctorId();
        this.patientId = appointment.getPatient().getPatientId();
        this.appointmentStatus = appointment.getAppointmentStatus();

    }

    public static List<AppointmentOut> fromAppointments(List<Appointment> appointment) {
        return appointment.stream()
                .map(AppointmentOut::new)
                .collect(Collectors.toList());
    }
}
