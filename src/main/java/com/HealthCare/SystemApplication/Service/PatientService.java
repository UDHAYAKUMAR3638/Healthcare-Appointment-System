package com.HealthCare.SystemApplication.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.PatientOut;
import com.HealthCare.SystemApplication.model.Patient;

@Service
public interface PatientService {
    public Patient getPatient(Long Id);

    public  Page<Patient> getAllPatients(Pageable pageable);

    public PatientOut updatePatient(Long id, Patient patient);

    public PatientOut deletePatient(Long id);

    public Patient getPatientEmail(String email);

}
