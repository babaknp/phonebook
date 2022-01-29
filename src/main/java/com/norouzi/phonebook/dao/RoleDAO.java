package com.norouzi.phonebook.dao;

import com.norouzi.phonebook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
