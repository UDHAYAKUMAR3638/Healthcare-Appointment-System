package com.HealthCare.SystemApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.model.Receptionist;

public interface ReceptionistRepo extends JpaRepository<Receptionist, Long> {

}
