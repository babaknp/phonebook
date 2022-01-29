package com.norouzi.phonebook.dao;

import com.norouzi.phonebook.model.PhonebookUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<PhonebookUsers,Integer> {
    PhonebookUsers findByUserName(String userName);
}
