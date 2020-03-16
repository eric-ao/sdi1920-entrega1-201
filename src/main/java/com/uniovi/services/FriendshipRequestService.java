package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendshipRequestsRepository;

@Service
public class FriendshipRequestService {

	@Autowired
	private FriendshipRequestsRepository frr;
	
	
	public void addFriendshipRequest(FriendshipRequest fr) {
		frr.save(fr);
	}
	
	public void deleteFriendshipRequest(Long id) {
		frr.deleteById(id);
	}
	
	public void sendFriendshipRequest(FriendshipRequest friendshipRequest, User userFrom) {
		frr.save(friendshipRequest);
	}
	
	//Devuelve solo las solicitudes pendientes
	public Page<FriendshipRequest> getRequests(Pageable pageable, User user) {
		return frr.findByEmailTo(pageable, user);
	}
	
	public FriendshipRequest getRequest(Long id) {
		return frr.findById(id).get();
	}
	
	public void acceptRequest(Long id) {
		FriendshipRequest aux = getRequest(id);
		deleteFriendshipRequest(id);
		aux.accept();
		addFriendshipRequest(aux);
	}
	
	public Page<User> getFriends(Pageable pageable, User user) {
		List<FriendshipRequest> list = frr.getFriends(pageable, user).getContent();
		List<User> friends = new ArrayList<User>();
		for(FriendshipRequest fr: list) {
			if(fr.getUserFrom() == user)
				friends.add(fr.getUserTo());
			else
				friends.add(fr.getUserFrom());
		}
		Page<User> friendList = new PageImpl<User>(friends, pageable, friends.size());
		return friendList;
	}
}
