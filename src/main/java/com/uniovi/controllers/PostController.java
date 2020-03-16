package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Post;
import com.uniovi.services.DateService;
import com.uniovi.services.PostsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddPostFormValidator;

@Controller
public class PostController {

	@Autowired
	private PostsService postService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private DateService dateService;
	
	@Autowired
	private AddPostFormValidator addPostFormValidator;
	
	@RequestMapping(value="/post/add", method=RequestMethod.POST)
	public String addPost(@Validated Post post, BindingResult result, Principal principal) {
		addPostFormValidator.validate(post, result);
		if(result.hasErrors()) {
			return "/post/add";
		}
		post.setAuthor(usersService.getUserByEmail(principal.getName()));
		post.setDate(dateService.dateToString(new Date()));
		postService.addPost(post);
		return "redirect:/post/list";
	}
	
	@RequestMapping(value = "/post/add")
	public String addPost(Model model) {
		model.addAttribute("post", new Post());
		return "post/add";
	}
	
	
	
	@RequestMapping(value = "/post/list")
	public String getPosts(Model model, Principal principal) {
		List<Post> posts = postService.getAllPosts(usersService.getUserByEmail(principal.getName()));
		model.addAttribute("postList", posts);
		return "post/list";
	}
}
