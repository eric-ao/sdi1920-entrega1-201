package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;

public interface FriendshipRequestsRepository extends CrudRepository<FriendshipRequest, Long>{

	@Query("SELECT r FROM FriendshipRequest r WHERE r.userTo = ?1 and r.status = 'PENDING'")
	Page<FriendshipRequest> findByEmailTo(Pageable pageable, User userTo);
	
	@Query("SELECT r FROM FriendshipRequest r WHERE (r.userFrom = ?1 or r.userTo = ?1) and r.status = 'ACCEPTED'")
	Page<FriendshipRequest> getFriends(Pageable pageable, User userFrom);
	
	@Query("SELECT r FROM FriendshipRequest r WHERE (r.userFrom = ?1 or r.userTo = ?1)")
	List<FriendshipRequest> getFriends(User userFrom);
}
