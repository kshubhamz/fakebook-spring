package com.kshz.fakebookserver.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.kshz.fakebookserver.controller.ConnectionController;
import com.kshz.fakebookserver.websocket.core.ChangeEvent;
import com.kshz.fakebookserver.websocket.core.Observer;

@Controller
public class ConnectionWSController implements Observer<ConnectionController> {
	
	private final SimpMessagingTemplate messagingTemplate;
	
	private ConnectionController controller;
	
	public ConnectionWSController(SimpMessagingTemplate messagingTemplate, ConnectionController connectionController) {
		this.messagingTemplate = messagingTemplate;
		this.controller = connectionController;
		this.controller.subscribe(this);
	}
	
	@Override
	public void handle(ChangeEvent<ConnectionController> args) {
		String message = args.getUsername() + " followed you.";
		this.messagingTemplate.convertAndSend("/connection/user/"+args.getTargetUser(), message);
	}
	
}
