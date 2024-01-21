package com.HealthCare.SystemApplication.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;

@Service
public interface AppointmentService {
    public AppointmentOut updateAppointment(Long id, Appointment appointment) throws IOException;

    public AppointmentOut updateDoctorAppointment(Long id, Appointment appointment) throws IOException;

    public AppointmentOut updatePatientAppointment(Long id, Appointment appointment) throws IOException;

    public boolean bookAppointment(Appointment appointment);

    public boolean isTimeOutsideRange(Long doctorId, LocalDateTime givenTime);

    public boolean isOutsideTimeRange(LocalDateTime givenTime, LocalDateTime appointmentTime);

    public void cancelAppointment(Long Id);

    public AppointmentOut getDoctorAppointment(Long Id);

    public AppointmentOut getPatientAppointment(Long Id);

    public List<Appointment> getAllAppointments();

    public String updateAppointmentStatus(Long id, String status) throws IOException;

}
