package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divya.inventorymanagement.Model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
