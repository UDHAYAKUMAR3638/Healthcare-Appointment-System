package com.divya.inventorymanagement.Service;

import java.util.List;

import com.divya.inventorymanagement.Model.Contact;

public interface ContactService {

    Contact addContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(int id);

    Contact updateContactById(int id, Contact contact);

    String deleteContactById(int id);
    
}
