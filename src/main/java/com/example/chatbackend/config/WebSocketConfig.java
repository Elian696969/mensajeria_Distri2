package com.example.chatbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // /topic → se usa para enviar mensajes a todos los clientes suscritos
        // /app → prefijo para los mensajes que van al controlador
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Este endpoint es el punto de conexión WebSocket que usará el frontend
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*")  // permite conexiones desde cualquier IP o dominio
                .withSockJS();                   // habilita soporte SockJS para navegadores sin WebSocket nativo
    }
}
