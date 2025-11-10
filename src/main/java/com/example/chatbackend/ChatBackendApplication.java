package com.example.chatbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ChatBackendApplication {

    public static void main(String[] args) {
        // Forzar creación del archivo chat-log.txt en la raíz del proyecto
        File logFile = new File("chat-log.txt"); // se genera en la raíz del proyecto
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
                System.out.println("📄 Archivo de log creado en: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo crear el archivo de log: " + e.getMessage());
        }

        SpringApplication.run(ChatBackendApplication.class, args);
        System.out.println("🚀 Chat Backend iniciado en http://localhost:8080");
        System.out.println("📡 WebSocket endpoint: ws://localhost:8080/chat");
    }
}
