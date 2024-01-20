package com.HealthCare.SystemApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Repository.AppointmentRepo;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.dto.AppointmentOut;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    public AppointmentOut updateAppointment(Long id, Appointment appointment) {
        Appointment appointment1 = appointmentRepo.findById(id).get();
        if (appointment1 == null)
            return null;
        else {
            if (appointment.getTime() != null)
                appointment1.setTime(appointment.getTime());
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            AppointmentOut AppointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            return AppointmentOut;
        }
    }

    public AppointmentOut updateDoctorAppointment(Long id, Appointment appointment) {
        Appointment appointment1 = appointmentRepo.findByDoctorDoctorId(id);
        if (appointment1 == null)
            return null;
        else {
            if (appointment.getTime() != null)
                appointment1.setTime(appointment.getTime());
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            AppointmentOut AppointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            return AppointmentOut;
        }
    }

    public AppointmentOut updatePatientAppointment(Long id, Appointment appointment) {
        Appointment appointment1 = appointmentRepo.findByPatientPatientId(id);
        if (appointment1 == null)
            return null;
        else {
            if (appointment.getTime() != null)
                appointment1.setTime(appointment.getTime());
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            AppointmentOut AppointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            return AppointmentOut;
        }
    }

    public void bookAppointment(Appointment appointment) {
        if (!appointmentRepo.existsById(appointment.getAppointmentId())) {
            appointmentRepo.save(appointment);
        }
    }

    public void cancelAppointment(Long Id) {
        appointmentRepo.deleteById(Id);
    }

    public AppointmentOut getDoctorAppointment(Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByDoctorDoctorId(Id);
        if (appointment != null) {
            AppointmentOut appointmentOut = new AppointmentOut(appointment);
            return appointmentOut;
        }
        return null;
    }

    public AppointmentOut getPatientAppointment(Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByPatientPatientId(Id);
        if (appointment != null) {
            AppointmentOut appointmentOut = new AppointmentOut(appointment);
            return appointmentOut;
        }
        return null;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }
}
