package com.norouzi.phonebook.api;


import com.norouzi.phonebook.model.Contact;
import com.norouzi.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/manageContact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/addContact")
    public Contact addContact(@RequestBody Contact contact){
        return contactService.addContact(contact);
    }

    @GetMapping(value = "/getContacts")
    public List<Contact> getContacts(){
        return contactService.getAllContactList();
    }

    @GetMapping(value = "/getContact")
    public Contact getSingleContact(@RequestBody Contact contact){
        return contactService.getContact(contact);
    }
    @PutMapping(value = "/updateContact/{contactId}")
    public Contact updateContact(@PathVariable("contactId") int contactId,@RequestBody Contact contact){
        return contactService.updateContact(contactId,contact);
    }

    @DeleteMapping(value = "/removeContact/{contactId}")
    public void removeContact(@PathVariable("contactId") int  contactId){
        contactService.removeContact(contactId);
    }

}
