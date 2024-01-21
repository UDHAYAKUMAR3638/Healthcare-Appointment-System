package com.HealthCare.SystemApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HealthCare.SystemApplication.service.AppointmentService;

@Controller
@RequestMapping("receptionist")
public class ReceptionistController {
    @Autowired
    AppointmentService appointmentService;

    @PutMapping("updateAppointmentStatus/{Id}/{status}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Long Id, @PathVariable String status) {
        try {
            return new ResponseEntity<String>(appointmentService.updateAppointmentStatus(Id, status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Status updation failed.", HttpStatus.NOT_FOUND);
        }
    }

}
