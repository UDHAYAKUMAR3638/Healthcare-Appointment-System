package com.HealthCare.SystemApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.Model.Receptionist;

public interface ReceptionistRepo extends JpaRepository<Receptionist, Long> {

}
