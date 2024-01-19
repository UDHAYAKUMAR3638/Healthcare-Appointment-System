package com.HealthCare.SystemApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Model.Appointment;
import com.HealthCare.SystemApplication.Model.Doctor;
import com.HealthCare.SystemApplication.Model.DoctorOut;
import com.HealthCare.SystemApplication.Repository.DoctorRepo;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public DoctorOut updateDoctor(Long id, Doctor doctor) {
        try {
            Doctor doctor1 = doctorRepo.findById(id).get();
            if (doctor1 == null)
                return null;
            else {
                if (doctor.doctorFristName != null)
                    doctor1.doctorFristName = doctor.doctorFristName;
                if (doctor.doctorLastName != null)
                    doctor1.doctorLastName = doctor.doctorLastName;
                if (doctor.doctorEmail != null)
                    doctor1.doctorEmail = doctor.doctorEmail;
                if (doctor.specialization != null)
                    doctor1.specialization = doctor.specialization;
                DoctorOut doctorOut = new DoctorOut(doctorRepo.save(doctor1));
                return doctorOut;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Appointment> getMyAppointments(Long docId) {
        Doctor myDoc = doctorRepo.findByDoctorId(docId);
        if (myDoc == null) {
            throw new IllegalStateException("The doctor does not exist");
        }
        return myDoc.getAppointments();
    }
}