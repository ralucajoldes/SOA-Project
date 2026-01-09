package com.toie.emailService.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Configuration
@AllArgsConstructor
public class RabbitMqConfig {
    public final static String EMAIL_QUEUE = "emailQueue";
    public final static String PRODUCT_COUNT_EXCHANGE = "emailQueue";
    public final static String PRODUCT_COUNT_ROUTING_KEY = "email";


    @Bean
    public Exchange emailExchange() {
        final var exchange = new TopicExchange(PRODUCT_COUNT_EXCHANGE);
        exchange.setShouldDeclare(true);
        return exchange;
    }

    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(EMAIL_QUEUE)
                .quorum()
                .deadLetterExchange(EMAIL_QUEUE + ".dlq")
                .deadLetterRoutingKey("deadLetter")
                .build();
    }

    @Bean
    public Binding emailQueueBinding(final Exchange emailExchange, final Queue emailQueue) {
        return BindingBuilder.bind(emailQueue).to(emailExchange)
                .with(PRODUCT_COUNT_ROUTING_KEY).noargs();
    }

    @Bean
    public Exchange emailDeadLetterExchange() {
        return new DirectExchange(PRODUCT_COUNT_EXCHANGE + ".dlx");
    }

    @Bean
    public Queue emailDeadLetterQueue() {
        return QueueBuilder.durable(EMAIL_QUEUE + ".dlq")
                .quorum()
                .build();
    }

    @Bean
    public Binding emailDeadLetterQueueBinding(final Exchange emailDeadLetterExchange, final Queue emailDeadLetterQueue) {
        return BindingBuilder.bind(emailDeadLetterQueue).to(emailDeadLetterExchange).with("deadLetter").noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        final JavaTimeModule module = new JavaTimeModule();
        final LocalDateDeserializer localDateTimeDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        module.addDeserializer(LocalDate.class, localDateTimeDeserializer);
        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(module)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();

        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
