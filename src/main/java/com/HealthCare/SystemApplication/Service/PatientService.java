package com.HealthCare.SystemApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.PatientOut;
import com.HealthCare.SystemApplication.model.Patient;

@Service
public interface PatientService {
    public Patient getPatient(Long Id);

    public List<Patient> getAllPatients();

    public PatientOut updatePatient(Long id, Patient patient);

    public PatientOut deletePatient(Long id);

}
