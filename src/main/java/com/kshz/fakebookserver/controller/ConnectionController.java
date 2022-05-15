package com.kshz.fakebookserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshz.fakebookserver.request.ConnectionReq;
import com.kshz.fakebookserver.response.ConnectionResponse;
import com.kshz.fakebookserver.service.ConnectionService;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;

	@PostMapping("/follow")
	public ConnectionResponse follow(@RequestBody @Valid ConnectionReq reqBody, HttpServletRequest req) {
		String userId = (String) req.getAttribute("userId");
		String actionUsername = reqBody.getUsername();
		connectionService.addFollowing(actionUsername, userId);
		return new ConnectionResponse("Followed Successfully.", "Started following " + actionUsername);
	}

	@DeleteMapping("/remove-follower")
	public ConnectionResponse removeFromFollower(@RequestBody @Valid ConnectionReq reqBody, HttpServletRequest req) {
		String userId = (String) req.getAttribute("userId");
		String actionUsername = reqBody.getUsername();
		connectionService.removeFollower(actionUsername, userId);
		return new ConnectionResponse("Removed from followers successfully.",
				actionUsername + " has been removed from the followers list.");
	}

	@DeleteMapping("/follow")
	public ConnectionResponse removeFromFollowing(@RequestBody @Valid ConnectionReq reqBody, HttpServletRequest req) {
		String userId = (String) req.getAttribute("userId");
		String actionUsername = reqBody.getUsername();
		connectionService.removeFollowing(actionUsername, userId);
		return new ConnectionResponse("Unfollowed successfully.", "Unfollowed " + actionUsername);
	}
}

