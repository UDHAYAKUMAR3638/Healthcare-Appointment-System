package com.HealthCare.SystemApplication.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.repository.DoctorRepo;
import com.HealthCare.SystemApplication.repository.PatientRepo;
import com.HealthCare.SystemApplication.repository.TokenRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;
import com.HealthCare.SystemApplication.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PatientRepo patientRepo;
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    TokenRepo tokenRepo;

    /* return user details */
    @Override
    public Optional<User> getUser(Integer Id) {
        return userRepo.findById(Id);
    }

    /* return all user details from user */
    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    /* delete user details from user table */
    @Override
    public String deleteUser(Integer Id) {
        User user = userRepo.findById(Id).get();
        if (user != null) {
            Token token = tokenRepo.findActiveTokensByUserId(Id);
            tokenRepo.delete(token);
            userRepo.delete(user);
            if (user.getRole().toString() == "PATIENT") {
                Patient patient = patientRepo.findByPatientEmail(user.getEmail());
                if (patient != null) {
                    appointmentRepo.deleteByPatientPatientId(patient.getPatientId());
                    patientRepo.delete(patient);
                }
            } else if (user.getRole().toString() == "DOCTOR") {
                Doctor doctor = doctorRepo.findByDoctorEmail(user.getEmail());
                if (doctor != null) {
                    appointmentRepo.deleteAllByDoctorDoctorId(doctor.getDoctorId());
                    doctorRepo.delete(doctor);
                }
            }
            return "User Deleted.";
        } else
            return "User not found.";

    }

}
