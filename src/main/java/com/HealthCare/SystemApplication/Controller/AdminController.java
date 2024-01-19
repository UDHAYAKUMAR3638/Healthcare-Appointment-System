package com.HealthCare.SystemApplication.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
public class AdminController {

    @PostMapping("/post")
    public String post() {
        return "ADMIN::POST";
    }

    @GetMapping("/get")
    public String get() {
        return "ADMIN::GET";
    }

    @PutMapping("/put")
    public String put() {
        return "ADMIN::PUT";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "ADMIN::DELETE";
    }

}
