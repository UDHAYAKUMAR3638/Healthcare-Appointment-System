package com.HealthCare.SystemApplication.service.implementation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.service.AppointmentService;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    /* update the appointment details */
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

    /* update the appointment details based on given doctorId */
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

    /* update the appointment details based on given patientId */
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

    /*
     * create new appointment with given details by checking whether any other
     * appointment is scheduled in the given time for this doctor and no other
     * appointment is present for this patient.
     */
    @Override
    public boolean bookAppointment(Appointment appointment) {
        if (isTimeOutsideRange(appointment.getDoctor().getDoctorId(), appointment.getTime())) {
            appointmentRepo.save(appointment);
            return true;
        } else
            return false;
    }

    /*
     * get appointment detail by doctor id and check its time.
     */
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

    /*
     * check given time is not in range of appointmentTime-15 and appointmentTime+15
     * mins
     */
    @Override
    public boolean isOutsideTimeRange(LocalDateTime givenTime, LocalDateTime appointmentTime) {

        LocalDateTime startTime = appointmentTime.minus(15, ChronoUnit.MINUTES);
        LocalDateTime endTime = appointmentTime.plus(15, ChronoUnit.MINUTES);
        return givenTime.isBefore(startTime) || givenTime.isAfter(endTime);
    }

    /* delete appointment based given on appointment id */
    @Override
    public void cancelAppointment(Long Id) {
        appointmentRepo.deleteById(Id);
    }

    /* return appointment detail based on given doctorId */
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

    /* return appointment detail based on given patientId */
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

    /* return list of all appointment details in appointment table */
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    /* update appointment status for mataining appointment is completed or not */
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
