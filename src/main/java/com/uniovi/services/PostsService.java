package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.repositories.PostsRepository;

@Service
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;
	
	
	
	public Post getPost(Long id) {
		return postsRepository.findById(id).get();
	}
	
	public void addPost(Post post) {
		postsRepository.save(post);
	}
	
	public void deletePost(Long id) {
		postsRepository.deleteById(id);
	}

	public List<Post> getAllPosts(User author) {
		return postsRepository.getAllByAuthor(author);
	}
	
}
