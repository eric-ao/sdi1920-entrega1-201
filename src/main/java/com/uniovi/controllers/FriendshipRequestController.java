package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.services.FriendshipRequestService;
import com.uniovi.services.UsersService;

@Controller
public class FriendshipRequestController {

	@Autowired
	private FriendshipRequestService friendshipRequestService;
	
	@Autowired
	private UsersService userService;

	
	
	@RequestMapping("/friendshiprequest/send/{id}")
	public String sendRequest(Model model, @PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName();
	    
	    User userTo = userService.getUser(id);
	    User userFrom = userService.getUserByEmail(email);
		friendshipRequestService.sendFriendshipRequest(new FriendshipRequest(userFrom, userTo), userFrom);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/friendshiprequest/list")
	public String getList(Model model, Principal principal, Pageable pageable) {
		User user = userService.getUserByEmail(principal.getName());
		
		Page<FriendshipRequest> frl = friendshipRequestService.getRequests(pageable, user);

		model.addAttribute("friendshipRequestList", frl.getContent());
		model.addAttribute("page", frl);
				
		return "friendshiprequest/list";
	}
	
	@RequestMapping("/friendshiprequest/accept/{id}")
	public String acceptRequest(Model model, Pageable pageable, Principal principal, @PathVariable Long id) {
		friendshipRequestService.acceptRequest(id);
		
		return "redirect:/friendshiprequest/list";
	}
	
}
