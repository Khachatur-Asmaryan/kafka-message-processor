package com.messageprocessor.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.messaging.kafka")
public class KafkaConfigProperties {
}
