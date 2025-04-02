# Spring Boot Kafka Project

## Overview
This project demonstrates how to set up and use Kafka in a Spring Boot application. It includes both producer and consumer implementations using JSON serialization and deserialization.

## Prerequisites
- Java 17 or later
- Apache Kafka (Download from [Kafka Official Website](https://kafka.apache.org/downloads))
- Spring Boot 3.x
- Maven or Gradle

## Running Kafka Locally
To run Kafka on your local machine, follow these steps:

1. **Start Zookeeper** (required for Kafka to work):
   ```sh
   bin\windows\zookeeper-server-start.bat ..\..\config\zookeeper.properties
   ```

2. **Start Kafka Server**:
   ```sh
   kafka-server-start.bat ..\..\config\server.properties
   ```

3. **Consume Messages from a Topic**:
   ```sh
   kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-topic --from-beginning
   ```

## Project Configuration
The `application.yml` file contains the necessary configurations for Kafka integration:
```yaml
server:
  port: 8081

spring:
  application:
    name: kafka-tutorials

  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: myGroup
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"

    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```

## API Endpoints
### 1. Publish a String Message to Kafka
**Endpoint:**
```
GET /api/v1/kafka/publish?message=HelloKafka
```
This sends a simple string message to Kafka.

### 2. Publish a JSON Message to Kafka
**Endpoint:**
```
POST /api/v1/kafka/publish1
```
**Request Body:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```
This sends a JSON object as a Kafka message.

## Common Issues & Fixes
### **1. `JsonDeserializer` ClassNotFoundException**
**Error:**
```
failed to convert java.lang.String to java.lang.Class<?> (caused by java.lang.ClassNotFoundException: org.apache.kafka.common.serialization.JsonDeserializer)
```
**Fix:** Use the correct deserializer:
```yaml
spring.kafka.consumer.value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
```

### **2. Missing `User` Parameter in JSON Request**
**Error:**
```
Required request parameter 'user' for method parameter type User is not present
```
**Fix:** Use `@RequestBody` instead of `@RequestParam`:
```java
@PostMapping("/publish1")
public ResponseEntity<String> publish(@RequestBody User user) {
    kafkaProducer.sendMessage(user);
    return ResponseEntity.ok("JSON message sent to Kafka topic");
}
```

## Running the Application
To start the Spring Boot application, use:
```sh
mvn spring-boot:run
```
OR
```sh
gradle bootRun
```

## Author
Developed by **Manav Lakhanpal**.

## License
This project is open-source and free to use.

