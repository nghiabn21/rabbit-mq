package com.rabbitmp.springboot_rabbitmq.controller;

import com.rabbitmp.springboot_rabbitmq.dto.Notification;
import com.rabbitmp.springboot_rabbitmq.publisher.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMqProducer.sendMessage(message);
        return ResponseEntity.ok("Sent message successfully ");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessageJson(@RequestBody Notification message) {
        rabbitMqProducer.sendMessageJson(message);
        return ResponseEntity.ok("Sent message for json successfully ");
    }
}
