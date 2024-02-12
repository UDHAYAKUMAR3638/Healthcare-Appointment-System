package com.HealthCare.SystemApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.model.Receptionist;

@Service
public interface ReceptionistService {
    public Receptionist getReceptionist(Long id);

    public List<Receptionist> getAllReceptionist();

    public Receptionist updateReceptionist(Long id, Receptionist receptionist);

    public Receptionist getReceptionistEmail(String email);
}
