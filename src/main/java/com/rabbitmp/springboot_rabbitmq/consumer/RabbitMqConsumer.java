package com.rabbitmp.springboot_rabbitmq.consumer;

import com.rabbitmp.springboot_rabbitmq.dto.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @Value("${spring.queue.name}")
    private String queueName;

    @Value("${spring.queue.name2}")
    private String queueNameJson;

    @RabbitListener(queues = {"${spring.queue.name}"})
    public void receive(String message) {
        LOGGER.info("Received from queue: {}", queueName + ", with message: " + message);
    }

    @RabbitListener(queues = {"${spring.queue.name2}"})
    public void receiveJson(Notification message) {
        LOGGER.info("Received from queue: {} , with message: {}", queueNameJson , message.toString());
    }
}
