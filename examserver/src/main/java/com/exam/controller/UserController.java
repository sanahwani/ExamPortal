package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*") //allowng all hosts(fr frntend)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bycryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        //encoding passowrd with bcrypt encoder
        user.setPassword(this.bycryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles=new HashSet<>();

        Role role=new Role();
        role.setRoleId(46L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return  this.userService.createUser(user,roles);
    }

    //get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){

        return  this.userService.getUser(username);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public  void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
//        return  ResponseEntity.notFound();
//    }
}

