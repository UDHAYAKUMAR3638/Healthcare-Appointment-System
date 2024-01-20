package com.HealthCare.SystemApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    Patient findFirstByPatientEmail(String userEmail);

    Patient findByPatientEmail(String email);

    // void deleteByEmail(String email);
}
