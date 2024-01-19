package com.HealthCare.SystemApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Model.Appointment;
import com.HealthCare.SystemApplication.Model.AppointmentOut;
import com.HealthCare.SystemApplication.Repository.AppointmentRepo;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    public AppointmentOut updateAppointment(Long id, Appointment appointment) {
        Appointment appointment1 = appointmentRepo.findById(id).get();
        if (appointment1 == null)
            return null;
        else {
            if (appointment.time != null)
                appointment1.time = appointment.time;
            if (appointment.doctor != null)
                appointment1.doctor = appointment.doctor;
            if (appointment.patient != null)
                appointment1.patient = appointment.patient;
            AppointmentOut AppointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            return AppointmentOut;
        }
    }

    public void bookAppointment(Appointment appointment) {
        if (!appointmentRepo.existsById(appointment.appointmentId)) {
            appointmentRepo.save(appointment);
        }
    }

    public void cancelAppointment(Long Id) {
        appointmentRepo.deleteById(Id);
    }
}
