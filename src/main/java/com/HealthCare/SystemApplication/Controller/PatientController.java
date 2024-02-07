package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.dto.PatientOut;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.service.PatientService;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentService appointmentService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT')")
    @GetMapping("/{Id}")
    public ResponseEntity<PatientOut> getPatient(@PathVariable Long Id) {
        Patient patient = null;
        patient = patientService.getPatient(Id);
        PatientOut patientOut = new PatientOut(patient);
        return new ResponseEntity<PatientOut>(patientOut, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientOut> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(patientService.updatePatient(id, patient));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @GetMapping("/getAll")
    public ResponseEntity<List<PatientOut>> getallPatients() {
        List<Patient> allPatients = null;
        allPatients = patientService.getAllPatients();
        List<PatientOut> allPatientsOut = PatientOut.fromPatients(allPatients);
        return new ResponseEntity<List<PatientOut>>(allPatientsOut, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    @GetMapping("/appointment/{Id}")
    public ResponseEntity<List<AppointmentOut>> getPatientAppointment(@PathVariable Long Id) {
        return new ResponseEntity<List<AppointmentOut>>(appointmentService.getPatientAppointment(Id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        HttpStatus status;
        try {
            return new ResponseEntity<>(patientService.deletePatient(id),
                    HttpStatus.OK);

        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, status);
    }

}
