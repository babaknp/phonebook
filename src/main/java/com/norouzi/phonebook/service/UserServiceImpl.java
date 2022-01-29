package com.norouzi.phonebook.service;

import com.norouzi.phonebook.dao.RoleDAO;
import com.norouzi.phonebook.dao.UserDAO;
import com.norouzi.phonebook.model.Role;
import com.norouzi.phonebook.model.PhonebookUsers;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
     private PasswordEncoder passwordEncoder ;
    @Override
    public PhonebookUsers saveUser(PhonebookUsers user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        PhonebookUsers user = userDAO.findByUserName(userName);
        Role role = roleDAO.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public PhonebookUsers getUser(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public List<PhonebookUsers> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       PhonebookUsers user = userDAO.findByUserName(userName);
       if(user == null){
           throw  new UsernameNotFoundException("user Not found");
       }else{

       }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRoles().forEach(role ->
               authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}
