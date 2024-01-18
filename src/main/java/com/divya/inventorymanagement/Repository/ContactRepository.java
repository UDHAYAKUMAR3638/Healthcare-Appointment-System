package com.divya.inventorymanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divya.inventorymanagement.Model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{
    
}
