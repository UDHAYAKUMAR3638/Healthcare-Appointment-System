package com.divya.inventorymanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.Appointment;
import com.divya.inventorymanagement.Model.Doctor;
import com.divya.inventorymanagement.Model.Patient;
import com.divya.inventorymanagement.Model.PatientOut;
import com.divya.inventorymanagement.Service.AppointmentService;
import com.divya.inventorymanagement.Service.PatientService;

@RestController
@RequestMapping("patient")
@PreAuthorize("hasRole('PATIENT') or hasRole('RECEPIONIST')")
public class PatientController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientService patientService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @GetMapping("/getAll")
    public ResponseEntity<List<PatientOut>> getallPatients() {
        List<Patient> allPatients = null;
        allPatients = patientService.getAllPatients();
        List<PatientOut> allPatientsOut = PatientOut.fromPatients(allPatients);
        return new ResponseEntity<List<PatientOut>>(allPatientsOut, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')")
    @PostMapping("/bookApp")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        String msg = null;
        HttpStatus status;
        try {
            appointmentService.bookAppointment(appointment);
            msg = " Appointment booked successfully";
            status = HttpStatus.OK;
        } catch (Exception e) {
            msg = "Book Another Appointment as this appointment is already booked";
            status = HttpStatus.BAD_REQUEST;
            appointmentService.bookAppointment(appointment);
        }
        return new ResponseEntity<String>(msg, status);
    }

    @DeleteMapping("/deleteApp/{Id}")
    ResponseEntity<String> cancelAppointment(@PathVariable Long Id) {
        appointmentService.cancelAppointment(Id);
        return new ResponseEntity<String>("Appointment Cancelled Sucessfully.", HttpStatus.OK);
    }

}
