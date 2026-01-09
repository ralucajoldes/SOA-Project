//package com.toie.shopApp.configuration.kafka;
//
//import com.lkww.trapla.ltn.reco.cmp.model.avro.RecommendationRequestAnalytics;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.UUIDSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.Map;
//import java.util.UUID;
//
//@Configuration
//public class AnalyticsProducerConfig {
//    @Bean
//    public KafkaTemplate<UUID, RecommendationRequestAnalytics> eventKafkaTemplate(KafkaProducerConfig kafkaProducerConfig) {
//        return new KafkaTemplate<>(eventProducerFactory(kafkaProducerConfig));
//    }
//
//    private ProducerFactory<UUID, RecommendationRequestAnalytics> eventProducerFactory(KafkaProducerConfig kafkaProducerConfig) {
//        Map<String, Object> props = kafkaProducerConfig.getConfiguration();
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SpecificAvroSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//}
