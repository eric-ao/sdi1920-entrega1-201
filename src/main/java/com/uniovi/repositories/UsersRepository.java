package com.uniovi.repositories;

import com.uniovi.entities.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.email != ?1 and u.role != 'ROLE_ADMIN'")
	Page<User> findOtherUsers(Pageable pageable, String email);
	
	@Query("SELECT u FROM User u WHERE u.email != ?1 and u.role != 'ROLE_ADMIN' and (LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.surname) LIKE LOWER(?1))")
	Page<User> searchByEmailNameAndSurname(Pageable pageable, String searchtext);
	
	List<User> findAll();
}
