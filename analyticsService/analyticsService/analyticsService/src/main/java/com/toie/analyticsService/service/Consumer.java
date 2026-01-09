package com.toie.analyticsService.service;

import com.toie.analyticsService.model.Analytics;
import com.toie.analyticsService.repository.AnalyticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class Consumer {
    private final AnalyticsRepository analyticsRepository;

    @KafkaListener(topics = "analytics", groupId = "group_id")
    public void consumeMessage(String productInfo) {
        analyticsRepository.save(Analytics.builder()
                .id(UUID.randomUUID().toString())
                .productInfo(productInfo)
                .build());
    }
}
