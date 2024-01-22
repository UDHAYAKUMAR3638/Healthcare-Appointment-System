package com.HealthCare.SystemApplication.model;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Receptionist.class, property = "receptionist_id")
public class Receptionist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receptionist_id;
    private String receptionistFristName;
    private String receptionistLastName;
    private String receptionistEmail;

    public Receptionist(User user) {
        this.receptionistFristName = user.getFirstname();
        this.receptionistLastName = user.getLastname();
        this.receptionistEmail = user.getEmail();
    }

}