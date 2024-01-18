package com.divya.inventorymanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.divya.inventorymanagement.Model.Appointment;
import com.divya.inventorymanagement.Model.Doctor;
import com.divya.inventorymanagement.Service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("doctor")
@PreAuthorize("hasRole('DOCTOR')")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return new ResponseEntity<String>("New Doctor Entity Created.", HttpStatus.CREATED);
    }

    @GetMapping("{docId}")
    ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId) {
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
        return new ResponseEntity<List<Appointment>>(myAppointments, status);

    }

}
