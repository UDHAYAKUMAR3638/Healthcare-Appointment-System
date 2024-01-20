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
    private Long appointmentId;
    private LocalDateTime time;
    private long doctorId;
    private long patientId;

    public AppointmentOut(Appointment appointment) {
        this.appointmentId = appointment.getAppointmentId();
        this.time = appointment.getTime();
        this.doctorId = appointment.getDoctor().getDoctorId();
        this.patientId = appointment.getPatient().getPatientId();

    }

    public static List<AppointmentOut> fromAppointments(List<Appointment> appointment) {
        return appointment.stream()
                .map(AppointmentOut::new)
                .collect(Collectors.toList());
    }
}
