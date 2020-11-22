package com.sangboak.micro.order.messaging;

import com.sangboak.micro.order.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private static final String TOPIC = "orders";

    final private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendMessage(String key, OrderCreatedEvent value) {
        logger.info(String.format("#### -> Producing message -> with key: " + key + " and value: " + value));
        this.kafkaTemplate.send(TOPIC, key, value);
    }
}
