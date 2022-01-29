package com.norouzi.phonebook.service;


import com.norouzi.phonebook.dao.ContactDAO;
import com.norouzi.phonebook.dao.UserDAO;
import com.norouzi.phonebook.model.Contact;
import com.norouzi.phonebook.model.PhonebookUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ContactService {

    @Autowired
    private ContactDAO contactDAO;
    @Autowired
    private UserDAO userDAO;
    private List<Contact> contactList = new CopyOnWriteArrayList<>();

    public Contact addContact(Contact contact){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        PhonebookUsers user = userDAO.findByUserName(username);
        contact.setPhonebookUsers(user);
        contactDAO.save(contact);
        return contact;
    }

    public List<Contact> getAllContactList(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        PhonebookUsers user = userDAO.findByUserName(username);
        return contactDAO.findAllByPhonebookUsers_Id(user.getId());
    }

    public Contact getContact(Contact contact){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        PhonebookUsers user = userDAO.findByUserName(username);
        return contactDAO.findByContactNameOrPhoneNumberAndPhonebookUsers_Id(contact.getContactName(),contact.getPhoneNumber(),user.getId());
    }


    public Contact updateContact(int contactId,Contact contact){
        Optional<Contact> contactTemp =contactDAO.findById(contactId);
        contactTemp.get().setContactName(contact.getContactName());
        contactTemp.get().setPhoneNumber(contact.getPhoneNumber());
        return contactDAO.save(contactTemp.get());
    }

    public void removeContact(int contactId){
        contactDAO.deleteById(contactId);
    }
}
