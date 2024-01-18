package com.divya.inventorymanagement.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
