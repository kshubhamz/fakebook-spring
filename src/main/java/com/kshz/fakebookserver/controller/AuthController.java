package com.kshz.fakebookserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kshz.fakebookserver.jwt.JWT;
import com.kshz.fakebookserver.model.User;
import com.kshz.fakebookserver.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWT jwt;

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User registerUser(@Valid @RequestBody User newUser) {
		User savedUser = userService.save(newUser);

		String jwtToken = jwt.generateToken(savedUser.getId(), 
				savedUser.getName(), 
				savedUser.getUsername(),
				savedUser.getEmail());

		savedUser.setToken(jwtToken);

		return savedUser;
	}

}
