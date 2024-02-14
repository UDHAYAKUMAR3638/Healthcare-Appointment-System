package com.HealthCare.SystemApplication.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthCare.SystemApplication.model.Receptionist;
import com.HealthCare.SystemApplication.model.User;
import com.HealthCare.SystemApplication.repository.ReceptionistRepo;
import com.HealthCare.SystemApplication.repository.UserRepo;
import com.HealthCare.SystemApplication.service.ReceptionistService;

@Service
public class ReceptionistServiceImp implements ReceptionistService {
    @Autowired
    ReceptionistRepo receptionistRepo;

    @Autowired
    UserRepo userRepo;
    /* return receptionist details */
    public Receptionist getReceptionist(Long id) {
        return receptionistRepo.findById(id).get();
    }

    public Receptionist getReceptionistEmail(String email) {
        return receptionistRepo.findByReceptionistEmail(email);
    }

    /* return all receptionist details */
    public List<Receptionist> getAllReceptionist() {
        return receptionistRepo.findAll();
    }

    /* update receptionist details based on given receptionistId */
    public Receptionist updateReceptionist(Long id, Receptionist receptionist) {
        Receptionist receptionist1 = receptionistRepo.findById(id).get();
        if (receptionist1 == null)
            return null;
        else {
            if (receptionist.getReceptionistFristName() != null)
                receptionist1.setReceptionistFristName(receptionist.getReceptionistFristName());
            if (receptionist.getReceptionistLastName() != null)
                receptionist1.setReceptionistLastName(receptionist.getReceptionistLastName());
                User user= userRepo.findByEmail(receptionist1.getReceptionistEmail()).get();
            if (receptionist.getReceptionistEmail() != null)
                receptionist1.setReceptionistEmail(receptionist.getReceptionistEmail());
                user.setEmail(receptionist1.getReceptionistEmail());
                user.setFirstname(receptionist1.getReceptionistFristName());
                user.setLastname(receptionist1.getReceptionistLastName());
                userRepo.save(user);
            Receptionist ReceptionistOut = receptionistRepo.save(receptionist1);
            return ReceptionistOut;
        }
    }

}
