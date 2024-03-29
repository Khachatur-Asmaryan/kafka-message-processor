package com.messageprocessor.config;

import com.messageprocessor.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {


    private final KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public ConsumerFactory<String, Employee> userConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getUrl());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Employee.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerFactory(CommonErrorHandler commonErrorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        factory.setCommonErrorHandler(commonErrorHandler);
        return factory;
    }
}
