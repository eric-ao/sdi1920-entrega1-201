package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FriendshipRequest {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private User userFrom;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userTo;
	
	private String status;
	
	
	public FriendshipRequest() {
		
	}
	
	public FriendshipRequest(User userFrom, User userTo) {
		super();
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.status = "PENDING";
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}
	
	public User getUserFrom() {
		return this.userFrom;
	}
	
	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}
	
	public User getUserTo() {
		return this.userTo;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void accept() {
		this.status = "ACCEPTED";
	}
	
	public void reject() {
		this.status = "REJECTED";
	}
	
	public Long getId() {
		return this.id;
	}
}