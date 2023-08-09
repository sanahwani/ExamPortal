package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
//@JsonComponent
public class ExamserverApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");

//		try {
//			// creating admin
//			User user = new User();
//			user.setFirstName("admin");
//			user.setLastName("dff");
//			user.setUsername("admin");
//			user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//			user.setEmail("sadmin@gmail.com");
//			user.setProfile("sdf/png");
//
//			Role role1 = new Role();
//			role1.setRoleId(44L);
//			role1.setRoleName("ADMIN");
//
//			Set<UserRole> userRoleSet = new HashSet<>();
//			UserRole userRole = new UserRole();
//
//			userRole.setRole(role1);
//
//			userRole.setUser(user);
//
//			userRoleSet.add(userRole);
//
//			User user1 = this.userService.createUser(user, userRoleSet);
//			System.out.println(user1.getUsername());
//
//		}catch(UserFoundException e){
//			e.printStackTrace();
//
//		}
	}
}
