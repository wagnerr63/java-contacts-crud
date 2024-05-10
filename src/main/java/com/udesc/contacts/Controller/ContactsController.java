package com.udesc.contacts.Controller;


import com.udesc.contacts.Entity.Contact;
import com.udesc.contacts.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public ResponseEntity<Contact> post(@RequestBody Contact contact) {
        contact.id = UUID.randomUUID().toString();
        Contact createdContact = contactRepository.insert(contact);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Contact>> get() {
        return new ResponseEntity<>(contactRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getByID(@PathVariable String id) {
        var found = this.contactRepository.getByID(id);
        if (found == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

}
