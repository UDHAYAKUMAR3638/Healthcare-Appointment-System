package com.divya.inventorymanagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.divya.inventorymanagement.Model.Appointment;
import com.divya.inventorymanagement.Model.Doctor;
import com.divya.inventorymanagement.Model.DoctorOut;
import com.divya.inventorymanagement.Model.Patient;
import com.divya.inventorymanagement.Model.PatientOut;
import com.divya.inventorymanagement.Service.AppointmentService;
import com.divya.inventorymanagement.Service.DoctorService;
import com.divya.inventorymanagement.Service.PatientService;

@Controller
@RequestMapping("receptionist")
@PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
public class ReceptionistController {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorOut>> getAllDoctors() {
        List<Doctor> allDoctors = null;
        allDoctors = patientService.getAllDoctors();
        List<DoctorOut> allDoctorsOut = DoctorOut.fromDoctors(allDoctors);
        return new ResponseEntity<List<DoctorOut>>(allDoctorsOut, HttpStatus.OK);

    }

    @GetMapping("/patient/{Id}")
    public ResponseEntity<PatientOut> getPatient(@PathVariable Long Id) {
        Patient patient = null;
        patient = patientService.getPatient(Id);
        PatientOut patientOut = new PatientOut(patient);
        return new ResponseEntity<PatientOut>(patientOut, HttpStatus.OK);

    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientOut>> getAllPatients() {
        List<Patient> allPatients = null;
        allPatients = patientService.getAllPatients();
        List<PatientOut> allPatientsOut = PatientOut.fromPatients(allPatients);
        return new ResponseEntity<List<PatientOut>>(allPatientsOut, HttpStatus.OK);

    }

    @PostMapping("/addDoctor")
    ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return new ResponseEntity<String>("New Doctor Entity Created.", HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{docId}")
    ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId) {
        List<Appointment> myAppointments = null;
        HttpStatus status;
        try {
            myAppointments = doctorService.getMyAppointments(docId);
            if (myAppointments.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            System.out.println("The doc Id is not valid");
            status = HttpStatus.BAD_REQUEST;

        }

        return new ResponseEntity<List<Appointment>>(myAppointments, status);

    }

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
