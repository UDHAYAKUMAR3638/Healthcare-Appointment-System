package com.HealthCare.SystemApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Repository.AppointmentRepo;
import com.HealthCare.SystemApplication.Repository.DoctorRepo;
import com.HealthCare.SystemApplication.Repository.TokenRepository;
import com.HealthCare.SystemApplication.Repository.UserRepository;
import com.HealthCare.SystemApplication.dto.DoctorOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.User;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    TokenRepository tokenRepo;

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public DoctorOut updateDoctor(Long id, Doctor doctor) {
        try {
            Doctor doctor1 = doctorRepo.findById(id).get();
            if (doctor1 == null)
                return null;
            else {
                if (doctor.doctorFristName != null)
                    doctor1.doctorFristName = doctor.doctorFristName;
                if (doctor.doctorLastName != null)
                    doctor1.doctorLastName = doctor.doctorLastName;
                if (doctor.doctorEmail != null)
                    doctor1.doctorEmail = doctor.doctorEmail;
                if (doctor.specialization != null)
                    doctor1.specialization = doctor.specialization;
                DoctorOut doctorOut = new DoctorOut(doctorRepo.save(doctor1));
                return doctorOut;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public DoctorOut deleteDoctor(Long id) {
        Doctor doctor = doctorRepo.findById(id).get();
        if (doctor == null)
            return null;
        else {
            List<Appointment> appointment = appointmentRepo.findAllByDoctorDoctorId(id);
            appointmentRepo.deleteAll(appointment);
            User user = userRepo.findByEmail(doctor.getDoctorEmail()).get();
            Token token = tokenRepo.findActiveTokensByUserId(user.getId());
            tokenRepo.delete(token);
            userRepo.deleteById(user.getId());
            DoctorOut doctorOut = new DoctorOut(doctor);
            doctorRepo.deleteById(id);
            return doctorOut;
        }
    }

    public List<Appointment> getDoctorAppointments(Long docId) {
        Doctor myDoc = doctorRepo.findByDoctorId(docId);
        if (myDoc == null) {
            throw new IllegalStateException("The doctor does not exist");
        }
        return myDoc.getAppointments();
    }
}