package com.springBoot.KafkaTutorials.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic kafkaGuideTopic() {
        return TopicBuilder.name("kafkaguides")
                .build();
    }

    @Bean
    public NewTopic kafkaGuideJsonTopic() {
        return TopicBuilder.name("kafkaguidesJson")
                .build();
    }
}
