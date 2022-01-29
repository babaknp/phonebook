package com.norouzi.phonebook.dao;


import com.norouzi.phonebook.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDAO extends CrudRepository<Contact,Integer> {

    @Override
    List<Contact> findAll();

    List<Contact> findAllByPhonebookUsers_Id(int userId);
    Contact findByContactNameOrPhoneNumberAndPhonebookUsers_Id(String contactName,String phoneNuber,int userId);
}
