package com.kshz.fakebookserver.request;

import com.kshz.fakebookserver.annotation.ConnectionAction;
import com.kshz.fakebookserver.annotation.ConnectionEntity;

public class ConnectionRequest {
	private String username;

	@ConnectionEntity(message = "type must be either follower or following")
	private String type;

	@ConnectionAction(message = "action must be either add or remove")
	private String action;

	public ConnectionRequest() {
	}

	public ConnectionRequest(String username, String type, String action) {
		this.username = username;
		this.type = type;
		this.action = action;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "ConnectionRequest [username=" + username + ", type=" + type + ", action=" + action + "]";
	}

}
