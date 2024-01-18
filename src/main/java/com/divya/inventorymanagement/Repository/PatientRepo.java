package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divya.inventorymanagement.Model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    Patient findFirstByPatientEmail(String userEmail);
}
