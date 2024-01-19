package com.divya.inventorymanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Auth.AuthenticationService;
import com.divya.inventorymanagement.Model.Doctor;
import com.divya.inventorymanagement.Model.Patient;
import com.divya.inventorymanagement.Repository.PatientRepo;

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

}