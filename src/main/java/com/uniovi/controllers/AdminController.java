package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.services.UsersService;

@Controller
public class AdminController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/admin/list")
	public String getList(Model model) {
		model.addAttribute("usersList", usersService.getAllUsers());
		return "admin/list";
	}	
}
