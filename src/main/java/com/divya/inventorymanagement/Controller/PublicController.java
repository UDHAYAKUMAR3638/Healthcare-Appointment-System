package com.divya.inventorymanagement.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MANAGER')")
public class PublicController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Public";
    }   

}