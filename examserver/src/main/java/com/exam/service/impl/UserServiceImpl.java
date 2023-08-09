package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl  implements UserService {

    //to save user and roles
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        //chck if user already exists in db
       User local = this.userRepository.findByUsername(user.getUsername());
       if(local!=null){
           System.out.println("User is already there");
           throw new UserFoundException();
       }else{
           //create user
           //since user can have many roles
           for(UserRole ur: userRoles){
               roleRepository.save(ur.getRole()); //saving roles
           }
           user.getUserRoles().addAll(userRoles);// setting all roles in user
          local= this.userRepository.save(user);
       }
        return local;
    }

    //get user by username
    @Override
    public User getUser(String username) {

        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);

    }


}
