package com.rabbitmp.springboot_rabbitmq.publisher;

import com.rabbitmp.springboot_rabbitmq.dto.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    @Value("${spring.exchange.name}")
    private String queueExchange;

    @Value("${spring.routing.name}")
    private String routingKey;

    @Value("${spring.routing.name2}")
    private String routingKeyJson;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String message) {
        LOGGER.info("Sending Message to Exchange {}, with message: {} ", queueExchange , message);
        rabbitTemplate.convertAndSend(queueExchange, routingKey, message);
    }

    public void sendMessageJson(Notification notification) {
        LOGGER.info("Sending Message to Exchange {}, with message: {} ", queueExchange , notification.toString());
        rabbitTemplate.convertAndSend(queueExchange, routingKeyJson, notification );
    }
}
