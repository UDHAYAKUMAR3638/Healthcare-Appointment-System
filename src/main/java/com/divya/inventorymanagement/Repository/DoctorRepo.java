package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divya.inventorymanagement.Model.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Doctor findByDoctorId(Long docId);
}
