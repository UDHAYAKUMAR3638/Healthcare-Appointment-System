package com.HealthCare.SystemApplication.controller;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.UserRepo;
import com.HealthCare.SystemApplication.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{Id}")
    ResponseEntity<User> getUser(@PathVariable Integer Id) {
        return new ResponseEntity<User>(userService.getUser(Id).get(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('RECEPTIONIST') or hasRole('PATIENT')")
    @GetMapping("/getEmail/{email}")
    ResponseEntity<User> getUser(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserEmail(email).get(), HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    // @GetMapping("/getAll")
    // ResponseEntity<List<User>> getAll() {
    //     return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    // }


@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/getAll")
public ResponseEntity<Page<User>> getItems(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<User> items = userRepo.findAll(pageable);
    return ResponseEntity.ok(items);
}


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{Id}")
    ResponseEntity<String> deleteUser(@PathVariable Integer Id) {
        try {
            return new ResponseEntity<String>(userService.deleteUser(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("User not deleted.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer Id,
            @RequestBody User user) {
        try {
            return new ResponseEntity<User>(userService.updateUser(Id, user),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
