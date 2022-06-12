package com.kshz.fakebookserver.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;

public class WebSocketExceptionHandler extends ExceptionWebSocketHandlerDecorator {

	public WebSocketExceptionHandler(WebSocketHandler delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}

}
