package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.dto.PatientOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Doctor;
import com.HealthCare.SystemApplication.model.Patient;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.service.PatientService;

@RestController
@CrossOrigin
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentService appointmentService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT') or hasRole('DOCTOR')")
    @GetMapping("/{Id}")
    public ResponseEntity<PatientOut> getPatient(@PathVariable Long Id) {
        Patient patient = null;
        patient = patientService.getPatient(Id);
        PatientOut patientOut = new PatientOut(patient);
        return new ResponseEntity<PatientOut>(patientOut, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT')")
    @GetMapping("/getEmail/{email}")
    public ResponseEntity<PatientOut> getPatientEmail(@PathVariable String email) {
        System.out.println("controller");
        Patient patient = null;
        patient = patientService.getPatientEmail(email);
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

    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT') or hasRole('DOCTOR') ")
    // @GetMapping("/getAll")
    // public ResponseEntity<List<PatientOut>> getallPatients() {
    //     List<Patient> allPatients = null;
    //     allPatients = patientService.getAllPatients();
    //     List<PatientOut> allPatientsOut = PatientOut.fromPatients(allPatients);
    //     return new ResponseEntity<List<PatientOut>>(allPatientsOut, HttpStatus.OK);
    // }


      @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT') or hasRole('DOCTOR')")
      @GetMapping("/getAll")
      public ResponseEntity<Page<Patient>> getallPatients(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Patient> items = patientService.getAllPatients(pageable);
            return ResponseEntity.ok(items);
        }

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST') or hasRole('ADMIN')  or hasRole('DOCTOR')")
    @GetMapping("/appointment/{Id}")
    public ResponseEntity<Page<Appointment>> getPatientAppointment(@PathVariable Long Id,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> items =appointmentService.getPatientAppointment(Id,pageable);
        return new ResponseEntity<Page<Appointment>>(items, HttpStatus.OK);
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
