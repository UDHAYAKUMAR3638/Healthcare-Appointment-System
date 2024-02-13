package com.HealthCare.SystemApplication.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.repository.DoctorRepo;
import com.HealthCare.SystemApplication.repository.PatientRepo;
import com.HealthCare.SystemApplication.repository.ReceptionistRepo;
import com.HealthCare.SystemApplication.repository.TokenRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;
import com.HealthCare.SystemApplication.service.DoctorService;
import com.HealthCare.SystemApplication.service.PatientService;
import com.HealthCare.SystemApplication.service.ReceptionistService;
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
    ReceptionistRepo receptionistRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    TokenRepo tokenRepo;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    ReceptionistService receptionistService;

    /* return user details */
    @Override
    public Optional<User> getUser(Integer Id) {
        return userRepo.findById(Id);
    }

    @Override
    public Optional<User> getUserEmail(String email) {
        return userRepo.findByEmail(email);
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
                Patient patient = patientRepo.findAllByPatientEmail(user.getEmail());
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

    /*
     * update user details also update in patient or doctor or receptionist detail
     * based on role
     */
    public User updateUser(Integer id, User user) {
        User user1 = userRepo.findById(id).get();
        if (user1 == null)
            return null;
        else {

            if (user.getFirstname() != null)
                user1.setFirstname(user.getFirstname());
            if (user.getLastname() != null)
                user1.setLastname(user.getLastname());

            if (user1.getRole().toString() == "PATIENT") {
                Patient patient = patientRepo.findAllByPatientEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                patientService.updatePatient(patient.getPatientId(), new Patient(user1));
            } else if (user1.getRole().toString() == "DOCTOR") {
                Doctor doctor = doctorRepo.findByDoctorEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                doctorService.updateDoctor(doctor.getDoctorId(), new Doctor(user1));
            } else if (user1.getRole().toString() == "RECEPTIONIST") {
                Receptionist receptionist = receptionistRepo.findByReceptionistEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                receptionistService.updateReceptionist(receptionist.getReceptionist_id(), new Receptionist(user1));
            } 
                if (user.getPhoneno() != null)
                    user1.setPhoneno(user.getPhoneno());
            User userOut = userRepo.save(user1);
            return userOut;
        }
    }

    public User updateUserByEmail(String email, User user) {
        User user1 = userRepo.findByEmail(email).get();
        if (user1 == null)
            return null;
        else {

            if (user.getFirstname() != null)
                user1.setFirstname(user.getFirstname());
            if (user.getLastname() != null)
                user1.setLastname(user.getLastname());

            if (user1.getRole().toString() == "PATIENT") {
                Patient patient = patientRepo.findAllByPatientEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                patientService.updatePatient(patient.getPatientId(), new Patient(user1));
            } else if (user1.getRole().toString() == "DOCTOR") {
                Doctor doctor = doctorRepo.findByDoctorEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                doctorService.updateDoctor(doctor.getDoctorId(), new Doctor(user1));
            } else if (user1.getRole().toString() == "RECEPTIONIST") {
                Receptionist receptionist = receptionistRepo.findByReceptionistEmail(user1.getEmail());
                if (user.getEmail() != null)
                    user1.setEmail(user.getEmail());
                receptionistService.updateReceptionist(receptionist.getReceptionist_id(), new Receptionist(user1));
            } else {
                if (user.getPhoneno() != null)
                    user1.setPhoneno(user.getPhoneno());
            }
            User userOut = userRepo.save(user1);
            return userOut;
        }
    }

}
