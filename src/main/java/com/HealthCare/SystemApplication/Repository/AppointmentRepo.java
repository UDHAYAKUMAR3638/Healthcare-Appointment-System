package com.HealthCare.SystemApplication.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.HealthCare.SystemApplication.model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    // Appointment findByDoctorId(Long id);
    // @Query("select * from appointment where doctor_id= ?0")

    List<Appointment> findAllByDoctorDoctorId(Long id);

    Appointment findByPatientPatientId(Long id);

    List<Appointment> findAllByPatientPatientId(Long id);

    List<Appointment> findByDoctorDoctorId(Long id);

    void deleteByPatientPatientId(Long patientId);

    void deleteAllByDoctorDoctorId(Long doctorId);

    Page<Appointment> findAllByPatientPatientId(Long id, Pageable pageable);

}
