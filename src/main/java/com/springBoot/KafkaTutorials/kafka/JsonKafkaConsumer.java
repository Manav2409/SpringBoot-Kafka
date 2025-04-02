package com.springBoot.KafkaTutorials.kafka;

import com.springBoot.KafkaTutorials.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "kafkaguidesJson", id = "myGroup")
    public void consume(User user) {
        logger.info(String.format("Json message received -> %s", user.toString()));
    }
}
