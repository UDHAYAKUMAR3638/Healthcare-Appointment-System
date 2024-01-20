package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.dto.AppointmentOut;
import com.HealthCare.SystemApplication.model.Appointment;
import com.HealthCare.SystemApplication.service.DoctorService;
import com.HealthCare.SystemApplication.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    // @Autowired
    // UserService userService;

    // @PreAuthorize("hasRole('ADMIN')")
    // @GetMapping("/details/{docId}")
    // ResponseEntity<User> getDoctorAppointments(@PathVariable Long docId) {
    // List<Appointment> myAppointments = null;
    // HttpStatus status;
    // try {
    // myAppointments = userService.getDoctorAppointments(docId);
    // if (myAppointments.isEmpty()) {
    // status = HttpStatus.NO_CONTENT;
    // } else {
    // status = HttpStatus.OK;
    // }
    // } catch (Exception e) {
    // System.out.println("The doc Id is not valid");
    // status = HttpStatus.BAD_REQUEST;

    // }
    // List<AppointmentOut> appointmentOut =
    // AppointmentOut.fromAppointments(myAppointments);
    // return new ResponseEntity<List<AppointmentOut>>(appointmentOut, status);
    // }
}
