package com.springBoot.KafkaTutorials.controller;

import com.springBoot.KafkaTutorials.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        try {
            kafkaProducer.sendMessage(message);
            logger.info("Message successfully sent: {}", message);
            return ResponseEntity.ok("Message sent successfully to Kafka topic.");
        } catch (Exception e) {
            logger.error("Error sending message to Kafka: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Failed to send message to Kafka.");
        }
    }
}
