package com.kshz.fakebookserver.response;

public class ConnectionResponse {
	private String message;
	private String details;

	public ConnectionResponse() {
	}

	public ConnectionResponse(String message, String details) {
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ConnectionResponse [message=" + message + ", details=" + details + "]";
	}
}
