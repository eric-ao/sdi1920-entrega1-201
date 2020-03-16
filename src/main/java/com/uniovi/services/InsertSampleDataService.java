package com.uniovi.services;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User admin = new User("admin", "Admin", "Admin");
		admin.setPassword("admin");
		admin.setRole(rolesService.getRoles()[1]);
		
		User admin2 = new User("admin@email.com", "Admin", "Admin");
		admin2.setPassword("admin");
		admin2.setRole(rolesService.getRoles()[1]);
		
		
		
		User user1 = new User("ted@mail.com", "Ted", "Elliott");
		user1.setPassword("12345");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("terry@mail.com", "Terry", "Rossio");
		user2.setPassword("12345");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("joe@mail.com", "Joe", "Stillman");
		user3.setPassword("12345");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("roger@mail.com", "Roger S.H.", "Schulman");
		user4.setPassword("12345");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("kelly@mail.com", "Kelly", "Asbury");
		user5.setPassword("12345");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("chris@mail.com", "Chris", "Miller");
		user6.setPassword("12345");
		user6.setRole(rolesService.getRoles()[0]);
		User user7 = new User("cody@mail.com", "Cody", "Cameron");
		user7.setPassword("12345");
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("conrad@mail.com", "Conrad", "Vernon");
		user8.setPassword("12345");
		user8.setRole(rolesService.getRoles()[0]);
		User user9 = new User("william@mail.com", "William", "Steig");
		user9.setPassword("12345");
		user9.setRole(rolesService.getRoles()[0]);
		User user10 = new User("jeffrey@mail.com", "Jeffrey", "Katzenberg");
		user10.setPassword("12345");
		user10.setRole(rolesService.getRoles()[0]);
		User user11 = new User("aron@mail.com", "Aron", "Warner");
		user11.setPassword("12345");
		user11.setRole(rolesService.getRoles()[0]);
		User user12 = new User("john@mail.com", "John H.", "Williams");
		user12.setPassword("12345");
		user12.setRole(rolesService.getRoles()[0]);
		User user13 = new User("penney@mail.com", "Penney", "Finkelman Cox");
		user13.setPassword("12345");
		user13.setRole(rolesService.getRoles()[0]);
		User user14 = new User("sandra@mail.com", "Sandra", "Rabins");
		user14.setPassword("12345");
		user14.setRole(rolesService.getRoles()[0]);
		User user15 = new User("david@mail.com", "David", "Lipman");
		user15.setPassword("12345");
		user15.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(admin);
		usersService.addUser(admin2);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user10);
		usersService.addUser(user11);
		usersService.addUser(user12);
		usersService.addUser(user13);
		usersService.addUser(user14);
		usersService.addUser(user15);
	}
}