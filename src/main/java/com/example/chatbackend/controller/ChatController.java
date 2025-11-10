package com.example.chatbackend.controller;

import com.example.chatbackend.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/send")   // Recibe mensajes normales
    @SendTo("/topic/messages") // Envía a todos los conectados
    public Message broadcast(Message message) {
        message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logger.info("Mensaje recibido de '{}': {}", message.getFrom(), message.getContent());
        return message;
    }

    @MessageMapping("/join")   // Usuario se conecta
    @SendTo("/topic/messages")
    public Message join(Message message) {
        message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logger.info("Usuario conectado: {}", message.getFrom());
        return message;
    }

    @MessageMapping("/leave")  // Usuario se desconecta
    @SendTo("/topic/messages")
    public Message leave(Message message) {
        message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logger.info("Usuario desconectado: {}", message.getFrom());
        return message;
    }
}
