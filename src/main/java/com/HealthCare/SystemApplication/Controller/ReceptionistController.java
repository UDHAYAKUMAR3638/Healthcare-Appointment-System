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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.service.ReceptionistService;

@RestController
@CrossOrigin
@RequestMapping("receptionist")
public class ReceptionistController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    ReceptionistService receptionistService;

    @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @PutMapping("updateAppointmentStatus/{Id}/{status}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long Id, @PathVariable String status) {
        try {
            return new ResponseEntity<String>(appointmentService.updateAppointmentStatus(Id, status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Status updation failed.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @GetMapping("/get/{Id}")
    public ResponseEntity<Receptionist> getReceptionist(@PathVariable Long Id) {
        try {
            return new ResponseEntity<Receptionist>(receptionistService.getReceptionist(Id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @GetMapping("/getEmail/{email}")
    public ResponseEntity<Receptionist> getReceptionistEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<Receptionist>(receptionistService.getReceptionistEmail(email),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Receptionist>> getAllReceptionist() {
        try {
            return new ResponseEntity<List<Receptionist>>(receptionistService.getAllReceptionist(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    // @GetMapping("/getAllAppointments")
    // public ResponseEntity<List<AppointmentOut>> getAllAppointments() {
    //     List<Appointment> myAppointments = null;
    //     HttpStatus status;
    //     try {
    //         myAppointments = appointmentService.getAllAppointments();
    //         if (myAppointments.isEmpty()) {
    //             status = HttpStatus.NO_CONTENT;
    //         } else {
    //             status = HttpStatus.OK;
    //         }
    //     } catch (Exception e) {
    //         status = HttpStatus.BAD_REQUEST;
    //     }
    //     List<AppointmentOut> appointmentOut = AppointmentOut.fromAppointments(myAppointments);
    //     return new ResponseEntity<List<AppointmentOut>>(appointmentOut, status);
    // }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    @GetMapping("/getAllAppointments")
    public ResponseEntity<Page<Appointment>> getAllAppointments(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> items = appointmentService.getAllAppointments(pageable);
        return ResponseEntity.ok(items);
    }

    @PreAuthorize("hasRole('RECEPTIONIST')or hasRole('ADMIN')")
    @GetMapping("/update/{Id}")
    public ResponseEntity<Receptionist> updateReceptionist(@PathVariable Long Id,
            @RequestBody Receptionist receptionist) {
        try {
            return new ResponseEntity<Receptionist>(receptionistService.updateReceptionist(Id, receptionist),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
