package com.HealthCare.SystemApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.HealthCare.SystemApplication.Model.Appointment;
import com.HealthCare.SystemApplication.Model.AppointmentOut;
import com.HealthCare.SystemApplication.Model.Doctor;
import com.HealthCare.SystemApplication.Model.DoctorOut;
import com.HealthCare.SystemApplication.Service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return new ResponseEntity<String>("New Doctor Entity Created.", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    @GetMapping("/appointmentDetails/{docId}")
    ResponseEntity<List<AppointmentOut>> getDocMyAppointments(@PathVariable Long docId) {
        List<Appointment> myAppointments = null;
        HttpStatus status;
        try {
            myAppointments = doctorService.getMyAppointments(docId);
            // System.out.println(myAppointments);
            if (myAppointments.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            System.out.println("The doc Id is not valid");
            status = HttpStatus.BAD_REQUEST;

        }
        List<AppointmentOut> appointmentOut = AppointmentOut.fromAppointments(myAppointments);
        return new ResponseEntity<List<AppointmentOut>>(appointmentOut, status);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @GetMapping("/getAll")
    public ResponseEntity<List<DoctorOut>> getAllDoctors() {
        List<Doctor> allDoctors = null;
        allDoctors = doctorService.getAllDoctors();
        List<DoctorOut> allDoctorsOut = DoctorOut.fromDoctors(allDoctors);
        return new ResponseEntity<List<DoctorOut>>(allDoctorsOut, HttpStatus.OK);

    }

}
