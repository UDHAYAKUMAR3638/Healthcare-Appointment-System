package com.HealthCare.SystemApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.Model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
