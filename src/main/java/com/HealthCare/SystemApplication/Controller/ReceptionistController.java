package com.HealthCare.SystemApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HealthCare.SystemApplication.service.AppointmentService;

@Controller
@RequestMapping("receptionist")
public class ReceptionistController {

    @Autowired
    AppointmentService appointmentService;

}
