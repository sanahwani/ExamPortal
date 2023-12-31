package com.exam.service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    //create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception; //will take user objct,  and set of user roles(it has user and role)

    //get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long userId);

}
