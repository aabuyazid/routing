package com.integration.routing.messagebroker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

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
    public MessageConverter deserializeJSON(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    private static final String dateTimeFormat= "yyyy-MM-dd HH:mm:ss";

    public AmqpTemplate ampqTemplate(ConnectionFactory conectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(conectionFactory);
        rabbitTemplate.setMessageConverter(deserializeJSON(new Jackson2ObjectMapperBuilder()));
        return rabbitTemplate;
    }
    // *** Messages of JSON END ***

    // *** Messages of RoutingRequest START ***
    @Value("${rabbitmq.routing.request.queue}")
    private String requestQueue;

    @Value("${rabbitmq.routing.request.exchange}")
    private String requestExchange;
    // spring bean for rabbitmq queue

    @Value("${rabbitmq.routing.request.routing.key}")
    private String requestRoutingKey;
    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueue);
    }

    @Bean
    public TopicExchange requestExchange() {
        return new TopicExchange(requestExchange);
    }

    @Bean
    public Binding requestBinding() {
        return BindingBuilder.bind(requestQueue())
                .to(requestExchange())
                .with(requestRoutingKey);
    }
// *** Messages of RoutingRequest END ***

// *** Messages of RoutingResponse START ***
@Value("${rabbitmq.routing.response.queue}")
private String responseQueue;

    @Value("${rabbitmq.routing.response.exchange}")
    private String responseExchange;
    // spring bean for rabbitmq queue

    @Value("${rabbitmq.routing.response.routing.key}")
    private String responseRoutingKey;
    @Bean
    public Queue responseQueue() {
        return new Queue(responseQueue);
    }

    @Bean
    public TopicExchange responseExchange() {
        return new TopicExchange(responseExchange);
    }

    @Bean
    public Binding responseBinding() {
        return BindingBuilder.bind(responseQueue())
                .to(responseExchange())
                .with(responseRoutingKey);
    }
// *** Messages of RoutingRequest END ***
}
