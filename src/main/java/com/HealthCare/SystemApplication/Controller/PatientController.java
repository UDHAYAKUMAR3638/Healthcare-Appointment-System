package com.HealthCare.SystemApplication.Controller;

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

import com.HealthCare.SystemApplication.Model.Appointment;
import com.HealthCare.SystemApplication.Model.AppointmentOut;
import com.HealthCare.SystemApplication.Model.Patient;
import com.HealthCare.SystemApplication.Model.PatientOut;
import com.HealthCare.SystemApplication.Repository.AppointmentRepo;
import com.HealthCare.SystemApplication.Service.AppointmentService;
import com.HealthCare.SystemApplication.Service.PatientService;

@RestController
@RequestMapping("patient")
@PreAuthorize("hasRole('PATIENT') or hasRole('RECEPIONIST')")
public class PatientController {
    @Autowired
    AppointmentRepo appointmentRepo;
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

    @PreAuthorize("hasRole('PATIENT') or hasRole('RECEPTIONIST')")
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
        }
        return new ResponseEntity<String>(msg, status);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('PATIENT')")
    @GetMapping("/appointment/{Id}")
    public ResponseEntity<AppointmentOut> getAppointment(@PathVariable Long Id) {
        Appointment appointment = null;
        appointment = appointmentRepo.findByPatientPatientId(Id);
        AppointmentOut appointmentOut = new AppointmentOut(appointment);
        return new ResponseEntity<AppointmentOut>(appointmentOut, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    @PutMapping("/updateApp/{id}")
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

    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    @DeleteMapping("/cancelApp/{Id}")
    ResponseEntity<String> cancelAppointment(@PathVariable Long Id) {
        appointmentService.cancelAppointment(Id);
        return new ResponseEntity<String>("Appointment Cancelled Sucessfully.", HttpStatus.OK);
    }

}
