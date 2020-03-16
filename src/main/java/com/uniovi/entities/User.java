package com.uniovi.entities;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String email;
	private String name;
	private String surname;
	private String password;
	@Transient
	private String passwordConfirm;
	private String role;
	
	@OneToMany(mappedBy = "userTo", cascade = CascadeType.ALL)
	private Set<FriendshipRequest> friendshipRequests;
	
	private ArrayList<User> friends = new ArrayList<User>();
	
	public User() {
		
	}
	
	public User(String email, String name, String surname) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return this.passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public boolean isFriend(User user) {
		boolean ret = false;
		for(User u: friends) {
			if(u == user)
				ret = true;
		}
		return ret;
		
	}
	
	public void addFriend(User user) {
		this.friends.add(user);
	}
}
