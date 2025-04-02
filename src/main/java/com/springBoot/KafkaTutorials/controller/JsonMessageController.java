package com.springBoot.KafkaTutorials.controller;

import com.springBoot.KafkaTutorials.kafka.JsonKafkaProducer;
import com.springBoot.KafkaTutorials.payload.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish1")
    public ResponseEntity<String> publish(@RequestBody User user) {
        kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("JSON message sent to Kafka topic");
    }
}
