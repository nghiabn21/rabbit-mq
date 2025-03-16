package com.rabbitmp.springboot_rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.13.7-management
public class RabbitMqConfig {

    @Value("${spring.queue.name}")
    private String queue;

    @Value("${spring.queue.name2}")
    private String queueJson;

    @Value("${spring.exchange.name}")
    private String queueExchange;

    @Value("${spring.routing.name}")
    private String routingKey;

    @Value("${spring.routing.name2}")
    private String routingKeyJson;

    //spring bean for rabbitMq
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // SPRING BEAN for queue json
    @Bean
    public Queue queueJson() {
        return new Queue(queueJson);
    }

    // create exchange ( nam o giua producer va queue: muc dich la chuyen thong tin toi queue can toi)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(queueExchange);
    }

    // chuyen hoa data tu exchange dua theo router_key
    // producer gui thong tin toi exchange
    // exchange se gui thong tin toi queue theo router_key ( bidding )
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // queue json
    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(queueJson())
                .to(exchange())
                .with(routingKeyJson);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean(name = "rabbitTemplateJson")
//    public AmqpTemplate rabbitTemplateJson(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter()); // Chuyển đổi JSON
//        return rabbitTemplate;
//    }


    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
    // => Spring boot autoconfiguration
}
