package com.sangboak.micro.kitchen.messaging;

import com.sangboak.micro.kitchen.dto.OrderCreatedEvent;
import com.sangboak.micro.kitchen.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class OrderConsumer {
    final private TicketService ticketService;
    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "orders", groupId = "group_id")
    public void consume(
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            OrderCreatedEvent message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s %s", key, message.getId()));
        ticketService.createTicket(message.getId(), message.getMenuName());
    }
}
