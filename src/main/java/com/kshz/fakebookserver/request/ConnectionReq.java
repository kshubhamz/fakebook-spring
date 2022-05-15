package com.kshz.fakebookserver.request;

import javax.validation.constraints.NotEmpty;

public class ConnectionReq {
	@NotEmpty(message = "username must not be empty")
	private String username;

	public ConnectionReq() {
	}

	public ConnectionReq(@NotEmpty(message = "username must not be empty") String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ConnectionReq [username=" + username + "]";
	}
}
