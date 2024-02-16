package com.HealthCare.SystemApplication.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.dto.DoctorOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Token;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.AppointmentRepo;
import com.HealthCare.SystemApplication.repository.DoctorRepo;
import com.HealthCare.SystemApplication.repository.TokenRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;
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

    /* return all doctor details from doctor table */
    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepo.findAll(pageable);
    }

    /* update doctor details by doctorId in doctor table */
    @Override
    public DoctorOut updateDoctor(Long id, Doctor doctor) {
        Doctor doctor1 = doctorRepo.findById(id).get();
        if (doctor1 == null)
            return null;
        else {
            if (doctor.getDoctorFristName() != null)
                doctor1.setDoctorFristName(doctor.getDoctorFristName());
            if (doctor.getDoctorLastName() != null)
                doctor1.setDoctorLastName(doctor.getDoctorLastName());
                User user=userRepo.findByEmail(doctor1.getDoctorEmail()).get();
            if (doctor.getDoctorEmail() != null)
                doctor1.setDoctorEmail(doctor.getDoctorEmail());
            if (doctor.getInTime() != null)
                doctor1.setInTime(doctor.getInTime());
            if (doctor.getOutTime() != null)
                doctor1.setOutTime(doctor.getOutTime());
            if (doctor.getSpecialization() != null)
                doctor1.setSpecialization(doctor.getSpecialization());
                user.setEmail(doctor1.getDoctorEmail());
                user.setFirstname(doctor1.getDoctorFristName());
                user.setLastname(doctor1.getDoctorLastName());
                userRepo.save(user);
            DoctorOut doctorOut = new DoctorOut(doctorRepo.save(doctor1));
            return doctorOut;
        }
    }

    /* delete doctor details by doctorId from doctor table */
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

    /* return doctor appointments by doctorId from doctor table */
    @Override
    public List<Appointment> getDoctorAppointments(Long docId) {
        Doctor myDoc = doctorRepo.findByDoctorId(docId);
        if (myDoc == null) {
            throw new IllegalStateException("The doctor does not exist");
        }
        return myDoc.getAppointments();
    }

    /* return doctor details by doctorId from doctor table */
    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepo.findById(id).get();
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
        return doctorRepo.findByDoctorEmail(email);
    }
}