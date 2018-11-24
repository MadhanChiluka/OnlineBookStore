package com.bookstore.api;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.api.config.SecurityUtility;
import com.bookstore.api.domain.User;
import com.bookstore.api.domain.security.Role;
import com.bookstore.api.domain.security.UserRole;
import com.bookstore.api.service.UserService;

@SpringBootApplication
public class BookStoreBackendApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreBackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User();
		user1.setFirstName("Madhan");
		user1.setLastName("Ch");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("madhanchiluka@gmail.com");
		
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		userRoles.clear();
		
		User user2 = new User();
		user2.setFirstName("anonymous");
		user2.setLastName("joker");
		user2.setUsername("anonymous");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user2.setEmail("anonymous@gmail.com");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, role2));
		
		userService.createUser(user2, userRoles);
	}
	
}

