package com.HealthCare.SystemApplication.service.implementation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Repository.AppointmentRepo;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.dto.AppointmentOut;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Override
    public AppointmentOut updateAppointment(Long id, Appointment appointment) throws IOException {
        Appointment appointment1 = appointmentRepo.findById(id).get();
        if (appointment1 == null)
            throw new IOException("Appointment Not found");
        else {
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            if (isTimeOutsideRange(appointment1.getDoctor().getDoctorId(), appointment.getTime())) {
                if (appointment.getTime() != null)
                    appointment1.setTime(appointment.getTime());
                AppointmentOut appointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
                return appointmentOut;
            }
        }
        throw new IOException("Appointment Not found");
    }

    @Override
    public AppointmentOut updateDoctorAppointment(Long id, Appointment appointment) throws IOException {

        Appointment appointment1 = appointmentRepo.findByDoctorDoctorId(id);
        if (appointment1 == null)
            throw new IOException("Appointment Not found");
        else {
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            if (isTimeOutsideRange(appointment1.getDoctor().getDoctorId(), appointment1.getTime())) {
                if (appointment.getTime() != null)
                    appointment1.setTime(appointment.getTime());
                AppointmentOut appointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
                return appointmentOut;
            }
        }
        throw new IOException("Appointment Not found");
    }

    @Override
    public AppointmentOut updatePatientAppointment(Long id, Appointment appointment) throws IOException {
        Appointment appointment1 = appointmentRepo.findByPatientPatientId(id);
        if (appointment1 == null)
            throw new IOException("Appointment Not found");
        else {
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            if (isTimeOutsideRange(appointment1.getDoctor().getDoctorId(), appointment1.getTime())) {
                if (appointment.getTime() != null)
                    appointment1.setTime(appointment.getTime());
                AppointmentOut appointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
                return appointmentOut;
            }
        }
        throw new IOException("Appointment Not found");
    }

    @Override
    public boolean bookAppointment(Appointment appointment) {
        if (isTimeOutsideRange(appointment.getDoctor().getDoctorId(), appointment.getTime())) {
            appointmentRepo.save(appointment);
            return true;
        } else
            return false;

    }

    @Override
    public boolean isTimeOutsideRange(Long doctorId, LocalDateTime givenTime) {

        List<Appointment> appointment = appointmentRepo.findAllByDoctorDoctorId(doctorId);
        if (appointment.isEmpty())
            return true;
        for (Appointment a : appointment) {
            if (!isOutsideTimeRange(givenTime, a.getTime()))
                return false;
        }
        return true;
    }

    @Override
    public boolean isOutsideTimeRange(LocalDateTime givenTime, LocalDateTime appointmentTime) {

        LocalDateTime startTime = appointmentTime.minus(15, ChronoUnit.MINUTES);
        LocalDateTime endTime = appointmentTime.plus(15, ChronoUnit.MINUTES);
        return givenTime.isBefore(startTime) || givenTime.isAfter(endTime);
    }

    @Override
    public void cancelAppointment(Long Id) {
        appointmentRepo.deleteById(Id);
    }

    @Override
    public AppointmentOut getDoctorAppointment(Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByDoctorDoctorId(Id);
        if (appointment != null) {
            AppointmentOut appointmentOut = new AppointmentOut(appointment);
            return appointmentOut;
        }
        return null;
    }

    @Override
    public AppointmentOut getPatientAppointment(Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByPatientPatientId(Id);
        if (appointment != null) {
            AppointmentOut appointmentOut = new AppointmentOut(appointment);
            return appointmentOut;
        }
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public String updateAppointmentStatus(Long id, String status) throws IOException {
        Appointment appointment1 = null;
        appointment1 = appointmentRepo.findById(id).get();
        if (appointment1 == null) {
            System.out.println("if");
            throw new IOException("Appointment Not found");
        } else {
            appointment1.setAppointmentStatus(status);
            appointmentRepo.save(appointment1);
            return "Status updated";
        }
    }

}
