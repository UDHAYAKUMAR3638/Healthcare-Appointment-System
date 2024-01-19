package com.HealthCare.SystemApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Auth.AuthenticationService;
import com.HealthCare.SystemApplication.Model.Patient;
import com.HealthCare.SystemApplication.Model.PatientOut;
import com.HealthCare.SystemApplication.Repository.PatientRepo;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    public Patient getPatient(Long Id) {
        return patientRepo.findById(Id).orElse(null);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public void cancelAppointment(Long key) {
        appointmentService.cancelAppointment(key);
    }

    public PatientOut updatePatient(Long id, Patient patient) {
        try {
            Patient patient1 = patientRepo.findById(id).get();
            if (patient1 == null)
                return null;
            else {
                patient1.patientFirstName = patient.patientFirstName;
                patient1.patientLastName = patient.patientLastName;
                patient1.patientContact = patient.patientContact;
                patient1.patientEmail = patient.patientEmail;
                PatientOut patientOut = new PatientOut(patientRepo.save(patient1));
                return patientOut;
            }
        } catch (Exception e) {
            return null;
        }
    }

}