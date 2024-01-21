package com.HealthCare.SystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Doctor findByDoctorId(Long docId);

    Doctor findByDoctorEmail(String email);

}
