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
            if (appointment.getTime() != null)
                appointment1.setTime(appointment.getTime());
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            ;
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

    public AppointmentOut getAppointment(Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByPatientPatientId(Id);
        AppointmentOut appointmentOut = new AppointmentOut(appointment);
        return appointmentOut;
    }
}
