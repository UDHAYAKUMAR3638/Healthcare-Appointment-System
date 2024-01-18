package com.divya.inventorymanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.Contact;
import com.divya.inventorymanagement.Service.ContactService;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;
    //Post request to add contact
    @PostMapping("/addcontact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        try {
            return ResponseEntity.ok(contactService.addContact(contact));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //Get request to get all contacts
    @GetMapping("/getallcontacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        try {
            return ResponseEntity.ok(contactService.getAllContacts());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //Get request to get contact by id
    @GetMapping("/getcontactbyid/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") int id){
        try {
            return ResponseEntity.ok(contactService.getContactById(id));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //Put request to update contact by id
    @PutMapping("/updatecontactbyid/{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable("id") int id,@RequestBody Contact contact){
        try {
            return ResponseEntity.ok(contactService.updateContactById(id,contact));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //Delete request to delete contact by id
    @DeleteMapping("/deletecontactbyid/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable("id") int id){
        try {
            contactService.deleteContactById(id);
            return ResponseEntity.ok("Contact Deleted Successfully");
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    
}
