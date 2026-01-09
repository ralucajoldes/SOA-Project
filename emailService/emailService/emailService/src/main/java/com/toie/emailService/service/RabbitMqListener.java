package com.toie.emailService.service;

import com.toie.emailService.mapper.EmailSentMapper;
import com.toie.emailService.model.EmailRequest;
import com.toie.emailService.repository.EmailSentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.toie.emailService.configuration.RabbitMqConfig.EMAIL_QUEUE;

@Service
@RequiredArgsConstructor
public class RabbitMqListener {
    private final EmailSentRepository emailSentRepository;
    private final EmailSentMapper emailSentMapper;

    @RabbitListener(
            queues = EMAIL_QUEUE,
            autoStartup = "true"
    )
    public void processCustomerOrder(EmailRequest emailRequest) {
        var emailSent = emailSentMapper.map(emailRequest, UUID.randomUUID().toString());
        emailSentRepository.save(emailSent);
    }
}
