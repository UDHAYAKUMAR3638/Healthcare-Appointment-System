package com.HealthCare.SystemApplication.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
    public LocalDateTime time;
    public long doctorId;
    public long patientId;

    public AppointmentOut(Appointment appointment) {
        this.appointmentId = appointment.appointmentId;
        this.time = appointment.time;
        this.doctorId = appointment.doctor.getDoctorId();
        this.patientId = appointment.patient.getPatientId();

    }

    public static List<AppointmentOut> fromAppointments(List<Appointment> appointment) {
        return appointment.stream()
                .map(AppointmentOut::new)
                .collect(Collectors.toList());
    }
}
