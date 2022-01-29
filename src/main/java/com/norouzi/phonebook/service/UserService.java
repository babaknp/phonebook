package com.norouzi.phonebook.service;
import com.norouzi.phonebook.model.Role;
import com.norouzi.phonebook.model.PhonebookUsers;
import java.util.List;

public interface UserService {
    PhonebookUsers saveUser(PhonebookUsers user);
    Role saveRole(Role role);
    void addRoleToUser(String userName,String roleName);
    PhonebookUsers getUser(String userName);
    List<PhonebookUsers> getUsers();
}
