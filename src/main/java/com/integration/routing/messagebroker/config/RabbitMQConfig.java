package com.integration.routing.messagebroker.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // *** Messages of Strings START ***
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    // spring bean for rabbitmq queue

    @Value("${rabbitmq.routingkey.name}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    // *** Messages of Strings END ***

    // *** Messages of JSON START ***
    @Value("${rabbitmq.json.queue.name}")
    private String queueJSON;

    @Value("${rabbitmq.json.exchange.name}")
    private String exchangeJSON;
    // spring bean for rabbitmq queue

    @Value("${rabbitmq.json.routingkey.name}")
    private String routingKeyJSON;
    @Bean
    public Queue queueJSON() {
        return new Queue(queueJSON);
    }

    @Bean
    public TopicExchange exchangeJSON() {
        return new TopicExchange(exchangeJSON);
    }

    @Bean
    public Binding bindingJSON() {
        return BindingBuilder.bind(queueJSON())
                .to(exchangeJSON())
                .with(routingKeyJSON);
    }

    // *** Messages of JSON START ***
    @Bean
    public MessageConverter deserializeJSON() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate ampqTemplate(ConnectionFactory conectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(conectionFactory);
        rabbitTemplate.setMessageConverter(deserializeJSON());
        return rabbitTemplate;
    }
    // *** Messages of JSON END ***
}
