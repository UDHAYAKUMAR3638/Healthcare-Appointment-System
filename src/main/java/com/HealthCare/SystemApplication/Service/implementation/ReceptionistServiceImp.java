package com.HealthCare.SystemApplication.service.implementation;

import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.repository.ReceptionistRepo;
import com.HealthCare.SystemApplication.service.ReceptionistService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistServiceImp implements ReceptionistService {
    @Autowired
    ReceptionistRepo receptionistRepo;

    /* return receptionist details */
    public Receptionist getReceptionist(Long id) {
        return receptionistRepo.findById(id).get();
    }

    /* return all receptionist details */
    public List<Receptionist> getAllReceptionist() {
        return receptionistRepo.findAll();

    }

}
