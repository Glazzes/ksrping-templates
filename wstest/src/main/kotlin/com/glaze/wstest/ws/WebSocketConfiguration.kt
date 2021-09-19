package com.glaze.wstest.ws

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration: WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // in order to use stomp is no necessary to user sockJs
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("http://localhost:19006/")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app")
            .setUserDestinationPrefix("/user")
            .setPreservePublishOrder(true)
            .enableSimpleBroker("/chatroom", "/profile")
            // enableSimpleBroker enables a message broker inside spring

            /*
            Special configuration required to log into a external broker like rabbitmq
            .enableStompBrokerRelay("/chatroom", "/profile")
            .setClientLogin("glaze") // username to log to the broker
            .setClientPasscode("dalmatian") // password to log to the broker
            .setSystemLogin("glaze") // username to log to the broker
            .setSystemPasscode("dalmatian") // password to log to the broker
            .setRelayHost("localhost")
            .setRelayPort(16003)
            */
    }
}