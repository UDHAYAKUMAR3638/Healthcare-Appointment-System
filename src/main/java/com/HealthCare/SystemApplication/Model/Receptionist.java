package com.HealthCare.SystemApplication.Model;

import com.HealthCare.SystemApplication.Users.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Receptionist.class, property = "ReceptionistId")
public class Receptionist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReceptionistId;
    private String ReceptionistFristName;
    private String ReceptionistLastName;
    private String ReceptionistEmail;

    public Receptionist(User user) {
        this.ReceptionistFristName = user.getFirstname();
        this.ReceptionistLastName = user.getLastname();
        this.ReceptionistEmail = user.getEmail();
    }

}