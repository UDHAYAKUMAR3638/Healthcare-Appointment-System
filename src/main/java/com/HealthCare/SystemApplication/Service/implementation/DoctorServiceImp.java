package com.HealthCare.SystemApplication.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.Repository.AppointmentRepo;
import com.HealthCare.SystemApplication.Repository.DoctorRepo;
import com.HealthCare.SystemApplication.Repository.TokenRepo;
import com.HealthCare.SystemApplication.Repository.UserRepo;
import com.HealthCare.SystemApplication.dto.DoctorOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.service.DoctorService;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenRepo tokenRepo;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public DoctorOut updateDoctor(Long id, Doctor doctor) {
        try {
            Doctor doctor1 = doctorRepo.findById(id).get();
            if (doctor1 == null)
                return null;
            else {
                if (doctor.getDoctorFristName() != null)
                    doctor1.setDoctorFristName(doctor.getDoctorFristName());
                if (doctor.getDoctorLastName() != null)
                    doctor1.setDoctorFristName(doctor.getDoctorLastName());
                if (doctor.getDoctorEmail() != null)
                    doctor1.setDoctorEmail(doctor.getDoctorEmail());
                if (doctor.getSpecialization() != null)
                    doctor1.setSpecialization(doctor.getSpecialization());
                DoctorOut doctorOut = new DoctorOut(doctorRepo.save(doctor1));
                return doctorOut;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DoctorOut deleteDoctor(Long id) {
        Doctor doctor = doctorRepo.findById(id).get();
        if (doctor == null)
            return null;
        else {
            List<Appointment> appointment = appointmentRepo.findAllByDoctorDoctorId(id);
            appointmentRepo.deleteAll(appointment);
            User user = userRepo.findByEmail(doctor.getDoctorEmail()).get();
            Token token = tokenRepo.findActiveTokensByUserId(user.getId());
            tokenRepo.delete(token);
            userRepo.deleteById(user.getId());
            DoctorOut doctorOut = new DoctorOut(doctor);
            doctorRepo.deleteById(id);
            return doctorOut;
        }
    }

    @Override
    public List<Appointment> getDoctorAppointments(Long docId) {
        Doctor myDoc = doctorRepo.findByDoctorId(docId);
        if (myDoc == null) {
            throw new IllegalStateException("The doctor does not exist");
        }
        return myDoc.getAppointments();
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepo.findById(id).get();
    }
}