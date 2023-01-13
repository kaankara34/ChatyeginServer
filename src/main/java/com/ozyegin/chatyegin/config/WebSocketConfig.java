package com.ozyegin.chatyegin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/websocket").setAllowedOriginPatterns("*").withSockJS(); // only connection
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) { // prefixes chatyegin
		registry.setApplicationDestinationPrefixes("/chatyegin");
		registry.enableSimpleBroker("/ozu-nero", "/ozu-members");
		registry.setUserDestinationPrefix("/ozu-members");
	}

	

}
