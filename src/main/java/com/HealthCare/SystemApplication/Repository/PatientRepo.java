package com.HealthCare.SystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HealthCare.SystemApplication.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    Patient findFirstByPatientEmail(String userEmail);

    @Query("SELECT p FROM Patient p WHERE p.patientEmail = :email")
    Patient findAllByPatientEmail(@Param("email") String email);

    // void deleteByEmail(String email);
}
