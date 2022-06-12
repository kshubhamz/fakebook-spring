package com.kshz.fakebookserver.websocket.core;

public class ChangeEvent<T> {
	private T source;
	private String username;
	private String targetUser;

	public ChangeEvent() {
	}

	public ChangeEvent(T source, String username, String targetUser) {
		this.source = source;
		this.username = username;
		this.targetUser = targetUser;
	}

	public T getSource() {
		return source;
	}

	public void setSource(T source) {
		this.source = source;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

}
