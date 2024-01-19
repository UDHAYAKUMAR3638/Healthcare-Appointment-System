package com.HealthCare.SystemApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.Model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    // Appointment findByDoctorId(Long id);
    // @Query("select * from appointment where doctor_id= ?0")

    List<Appointment> findAllByDoctorDoctorId(Long id);
}
