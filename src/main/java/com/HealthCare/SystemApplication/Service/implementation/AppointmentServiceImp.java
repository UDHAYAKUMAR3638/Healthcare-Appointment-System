package com.HealthCare.SystemApplication.service.implementation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.repository.DoctorRepo;
import com.HealthCare.SystemApplication.service.AppointmentService;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    DoctorRepo doctorRepo;

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
        Appointment appointment1 = appointmentRepo.findByDoctorDoctorId(id).get(0);
        AppointmentOut appointmentOut = null;
        if (appointment1 == null)
            throw new IOException("Appointment Not found");
        else {
            if (appointment.getPatient() != null)
                appointment1.setPatient(appointment.getPatient());
            if (isTimeOutsideRange(appointment1.getDoctor().getDoctorId(), appointment1.getTime())) {
                if (appointment.getTime() != null)
                    appointment1.setTime(appointment.getTime());
                appointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            }
        }
        return appointmentOut;
    }

    /* update the appointment details based on given patientId */
    @Override
    public AppointmentOut updatePatientAppointment(Long id, Appointment appointment) throws IOException {
        Appointment appointment1 = appointmentRepo.findByPatientPatientId(id);
        AppointmentOut appointmentOut = null;
        if (appointment1 == null)
            throw new IOException("Appointment Not found");
        else {
            if (appointment.getDoctor() != null)
                appointment1.setDoctor(appointment.getDoctor());
            if (isTimeOutsideRange(appointment1.getDoctor().getDoctorId(), appointment1.getTime())) {
                if (appointment.getTime() != null)
                    appointment1.setTime(appointment.getTime());
                appointmentOut = new AppointmentOut(appointmentRepo.save(appointment1));
            }
        }
        return appointmentOut;

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
        Doctor doctor = doctorRepo.findByDoctorId(doctorId);
        if (appointment.isEmpty())
            return true;
        if (isDoctorAvailable(givenTime, doctor.getInTime(), doctor.getOutTime())) {
            System.out.println("Doctor is avaliable at given time");
            for (Appointment a : appointment) {
                if (!isOutsideTimeRange(givenTime, a.getTime()))
                    return false;
            }
            return true;
        }
        System.out.println("Doctor is not avaliable at given time");
        return false;
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

    /*
     * check given time is in between doctor inTime and outTime
     */
    @Override
    public boolean isDoctorAvailable(LocalDateTime givenTime, LocalDateTime inTime, LocalDateTime outTime) {
        return givenTime.isAfter(inTime) && givenTime.isBefore(outTime);
    }

    /* delete appointment based given on appointment id */
    @Override
    public void cancelAppointment(Long Id) {
        appointmentRepo.deleteById(Id);
    }

    /* return appointment detail based on given doctorId */
    @Override
    public List<AppointmentOut> getDoctorAppointment(Long Id) {
        List<Appointment> appointment = null;
        appointment = appointmentRepo.findByDoctorDoctorId(Id);
        if (appointment != null) {
            return AppointmentOut.fromAppointments(appointment);
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
