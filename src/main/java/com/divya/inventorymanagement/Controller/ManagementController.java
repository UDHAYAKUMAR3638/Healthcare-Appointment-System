package com.divya.inventorymanagement.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
public class ManagementController {

    @PostMapping("/post")
    public String post(){
        return "MANAGER::POST";
    }

    @GetMapping("/get")
    public String get(){
        return "MANAGER::GET";
    }

    @PutMapping("/put")
    public String put(){
        return "MANAGER::PUT";
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "MANAGER::DELETE";
    }
    
}
