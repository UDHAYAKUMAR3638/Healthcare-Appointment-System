package com.HealthCare.SystemApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HealthCare.SystemApplication.Service.AppointmentService;
import com.HealthCare.SystemApplication.Service.DoctorService;
import com.HealthCare.SystemApplication.Service.PatientService;

@Controller
@RequestMapping("receptionist")
public class ReceptionistController {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

}
