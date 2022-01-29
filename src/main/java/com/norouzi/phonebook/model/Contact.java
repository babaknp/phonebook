package com.norouzi.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int contactId;
    @JsonProperty("name")
    private String contactName;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private PhonebookUsers phonebookUsers;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhonebookUsers getPhonebookUsers() {
        return phonebookUsers;
    }

    public void setPhonebookUsers(PhonebookUsers phonebookUsers) {
        this.phonebookUsers = phonebookUsers;
    }
}
