package com.HealthCare.SystemApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.DoctorOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;

@Service
public interface DoctorService {

    public Page<Doctor> getAllDoctors(Pageable pageable);

    public DoctorOut updateDoctor(Long id, Doctor doctor);

    public DoctorOut deleteDoctor(Long id);

    public List<Appointment> getDoctorAppointments(Long docId);

    public Doctor getDoctor(Long id);

    public Doctor getDoctorByEmail(String email);

}