package com.example.chatbackend.config;
import com.example.chatbackend.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            logger.info("Usuario desconectado: {}", username);

            // Enviar mensaje de tipo LEAVE a todos los suscriptores
            Message leaveMessage = new Message();
            leaveMessage.setFrom(username);
            leaveMessage.setType("LEAVE");
            leaveMessage.setContent("ha salido del chat");

            messagingTemplate.convertAndSend("/topic/messages", leaveMessage);
        }
    }
}
