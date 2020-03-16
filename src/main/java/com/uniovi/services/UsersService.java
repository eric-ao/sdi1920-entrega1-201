package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendshipRequestsRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private FriendshipRequestsRepository frr;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public Page<User> searchByEmailNameAndSurname(Pageable pageable, String searchtext, User userFrom) {
		getFriends(userFrom, frr.getFriends(userFrom));
		searchtext = "%"+searchtext+"%";
		return usersRepository.searchByEmailNameAndSurname(pageable, searchtext);
	}
	
	//No devuelve usuarios admin o el propio usuario de la sesión.
	public Page<User> getUsers(Pageable pageable, String email, User userFrom) {		
		getFriends(userFrom, frr.getFriends(userFrom));
		return usersRepository.findOtherUsers(pageable, email); 
	}
	
	public Page<User> getAllUsers(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}
	
	public List<User> getAllUsers() {
		return usersRepository.findAll();
	}
	
	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public void getFriends(User userfrom, List<FriendshipRequest> friends) {
		for(FriendshipRequest fr: friends) {
			if(fr.getUserFrom() != userfrom) {
				fr.getUserFrom().addFriend(userfrom);
				userfrom.addFriend(fr.getUserFrom());
			} else {
				fr.getUserTo().addFriend(userfrom);
				userfrom.addFriend(fr.getUserTo());
			}
		}
	}
	
}
