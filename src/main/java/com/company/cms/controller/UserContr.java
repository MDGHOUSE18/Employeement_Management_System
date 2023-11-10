package com.company.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.cms.entity.User;
import com.company.cms.service.UserService;
@RestController
public class UserContr {
		@Autowired
		private UserService userService;
		
		@GetMapping(value = "/users")  
		public List<User> getAllUsers()   
		{  
		  
		return userService.getAllUsers();
		}
}
