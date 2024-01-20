package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.service.AppointmentService;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')")
    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        String msg = null;
        HttpStatus status;
        try {
            if (appointmentService.bookAppointment(appointment))
                msg = " Appointment booked successfully";
            else
                msg = "Another Appointment is already booked at this time";
            status = HttpStatus.OK;
        } catch (Exception e) {
            msg = "Book Another Appointment as this appointment is already booked";
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<String>(msg, status);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        HttpStatus status;
        try {
            return new ResponseEntity<AppointmentOut>(appointmentService.updateAppointment(id, appointment),
                    HttpStatus.OK);

        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, status);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/updateByDoctorId/{id}")
    public ResponseEntity<?> updateByDoctorAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        HttpStatus status;
        try {
            return new ResponseEntity<AppointmentOut>(appointmentService.updateDoctorAppointment(id, appointment),
                    HttpStatus.OK);

        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, status);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PutMapping("/updateByPatientId/{id}")
    public ResponseEntity<?> updateByPatientAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        HttpStatus status;
        try {
            return new ResponseEntity<AppointmentOut>(appointmentService.updatePatientAppointment(id, appointment),
                    HttpStatus.OK);

        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, status);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/patientAppointment/{Id}")
    public ResponseEntity<AppointmentOut> getPatientAppointment(@PathVariable Long Id) {
        return new ResponseEntity<AppointmentOut>(appointmentService.getPatientAppointment(Id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctorAppointment/{Id}")
    public ResponseEntity<AppointmentOut> getDoctorAppointment(@PathVariable Long Id) {
        return new ResponseEntity<AppointmentOut>(appointmentService.getDoctorAppointment(Id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    @GetMapping("/getAll")
    ResponseEntity<List<AppointmentOut>> getAllAppointments() {
        List<Appointment> myAppointments = null;
        HttpStatus status;
        try {
            myAppointments = appointmentService.getAllAppointments();
            if (myAppointments.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;

        }
        List<AppointmentOut> appointmentOut = AppointmentOut.fromAppointments(myAppointments);
        return new ResponseEntity<List<AppointmentOut>>(appointmentOut, status);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    @DeleteMapping("/cancel/{Id}")
    ResponseEntity<String> cancelAppointment(@PathVariable Long Id) {
        appointmentService.cancelAppointment(Id);
        return new ResponseEntity<String>("Appointment Cancelled Sucessfully.", HttpStatus.OK);
    }
}
