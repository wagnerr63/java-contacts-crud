package com.udesc.contacts.Repository;

import com.udesc.contacts.Entity.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;

@Repository
public class ContactRepository {
    private ArrayList<Contact> contacts;

    public ContactRepository() {
        this.contacts = new ArrayList<Contact>();
    }


    public Contact insert(Contact contact) {
        this.contacts.add(contact);
        return contact;
    }

    public Contact getByID(String id) {
        for(Contact contact: this.contacts) {
            if (Objects.equals(contact.id, id)) {
                return contact;
            }
        }

        return null;
    }

    public ArrayList<Contact> getAll() {
        return this.contacts;
    }

    public Contact update(Contact contact) throws Exception {
        var found = this.getByID(contact.id);
        if (found == null) {
            throw new Exception("user not found");
        }

        this.contacts.remove(found);
        this.contacts.add(contact);

        return contact;
    }

    public void delete(String id) throws Exception {
        var found = this.getByID(id);
        if (found == null) {
            throw new Exception("user not found");
        }

        this.contacts.remove(found);
    }
}
