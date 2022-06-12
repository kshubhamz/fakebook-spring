package com.kshz.fakebookserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Value("${cors.origin.allow}")
	private String allowedOrigins;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//StompWebSocketEndpointRegistration addEndpoint = registry.addEndpoint("/socket/**");
		
		/*Arrays.asList(allowedOrigins.split(",")).stream()
			.forEach(origin -> addEndpoint.setAllowedOrigins(origin.trim()));*/
		registry.addEndpoint("/ws")
			.setAllowedOrigins("http://localhost:4200")
			.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/connection");
		registry.setUserDestinationPrefix("/user");
	}
	
}