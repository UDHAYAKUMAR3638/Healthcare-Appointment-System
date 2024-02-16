package com.HealthCare.SystemApplication.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.PatientOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.repository.PatientRepo;
import com.HealthCare.SystemApplication.repository.TokenRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;
import com.HealthCare.SystemApplication.service.AuthenticationService;
import com.HealthCare.SystemApplication.service.PatientService;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    AppointmentServiceImp appointmentService;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenRepo tokenRepo;

    /* return patient detail from patient table by patientId */
    @Override
    public Patient getPatient(Long Id) {
        return patientRepo.findById(Id).orElse(null);
    }

    @Override
    public Patient getPatientEmail(String email) {
        return patientRepo.findAllByPatientEmail(email);
    }

    /* return patient appointment detail from appointment table */
    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepo.findAll(pageable);
    }

    /* update patient detail by patientId */
    @Override
    public PatientOut updatePatient(Long id, Patient patient) {
        Patient patient1 = patientRepo.findById(id).get();
        if (patient1 == null)
            return null;
        else {
            if (patient.getPatientFirstName() != null)
                patient1.setPatientFirstName(patient.getPatientFirstName());
            if (patient.getPatientLastName() != null)
                patient1.setPatientLastName(patient.getPatientLastName());
            if (patient.getPatientContact() != null)
                patient1.setPatientContact(patient.getPatientContact());
                User user=userRepo.findByEmail(patient1.getPatientEmail()).get();
            if (patient.getPatientEmail() != null)
                patient1.setPatientEmail(patient.getPatientEmail());
                user.setEmail(patient1.getPatientEmail());
                user.setFirstname(patient1.getPatientFirstName());
                user.setLastname(patient1.getPatientLastName());
                user.setPhoneno(patient1.getPatientContact());
                userRepo.save(user);
            PatientOut patientOut = new PatientOut(patientRepo.save(patient1));
            return patientOut;
        }
    }

    /* delete patient detail by patientId */
    @Override
    public PatientOut deletePatient(Long id) {
        Patient patient = patientRepo.findById(id).get();
        if (patient == null)
            return null;
        else {
            List<Appointment> appointment = appointmentRepo.findAllByPatientPatientId(id);
            appointmentRepo.deleteAll(appointment);
            User user = userRepo.findByEmail(patient.getPatientEmail()).get();
            Token token = tokenRepo.findActiveTokensByUserId(user.getId());
            tokenRepo.delete(token);
            userRepo.deleteById(user.getId());
            PatientOut patientOut = new PatientOut(patient);
            patientRepo.deleteById(id);
            return patientOut;
        }
    }

}