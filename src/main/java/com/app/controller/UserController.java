package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RegisterRequestDTO;
import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody RegisterRequestDTO registerRequestDTO){
		return ResponseEntity.ok().body(userService.userRegister(registerRequestDTO));
	}
}
