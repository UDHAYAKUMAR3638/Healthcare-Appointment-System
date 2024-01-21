package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.service.AppointmentService;
import com.HealthCare.SystemApplication.service.ReceptionistService;

@RestController
@RequestMapping("receptionist")
public class ReceptionistController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    ReceptionistService receptionistService;

    @PreAuthorize("hasRole('RECEPTIONIST')")
    @PutMapping("updateAppointmentStatus/{Id}/{status}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long Id, @PathVariable String status) {
        try {
            return new ResponseEntity<String>(appointmentService.updateAppointmentStatus(Id, status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Status updation failed.", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('RECEPTIONIST')")
    @GetMapping("/get/{Id}")
    public ResponseEntity<Receptionist> getReceptionist(@PathVariable Long Id) {
        try {
            return new ResponseEntity<Receptionist>(receptionistService.getReceptionist(Id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('RECEPTIONIST')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Receptionist>> getAllReceptionist() {
        try {
            return new ResponseEntity<List<Receptionist>>(receptionistService.getAllReceptionist(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
