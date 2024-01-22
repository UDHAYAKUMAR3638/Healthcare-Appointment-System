package com.HealthCare.SystemApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.model.Receptionist;

public interface ReceptionistRepo extends JpaRepository<Receptionist, Long> {

    Receptionist findByReceptionistEmail(String email);

}
