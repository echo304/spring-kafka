package com.sangboak.micro.kitchen.messaging;

import com.sangboak.micro.kitchen.dto.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketProducer {
    private static final Logger logger = LoggerFactory.getLogger(TicketProducer.class);
    private static final String TOPIC = "tickets";

    final private KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate;

    public void sendMessage(String key, TicketCreatedEvent ticketCreatedEvent) {
        logger.info(String.format("#### -> Producing message " + "on " + TOPIC +  " -> with key: " + key));
        this.kafkaTemplate.send(TOPIC, "TICKET_CREATED", ticketCreatedEvent);
    }
}
