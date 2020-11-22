package com.sangboak.micro.order.messaging;

import com.sangboak.micro.order.dto.OrderAcceptedEvent;
import com.sangboak.micro.order.entity.Order;
import com.sangboak.micro.order.entity.OrderStatus;
import com.sangboak.micro.order.repository.OrderRepository;
import com.sangboak.micro.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class TicketConsumer {
    final private OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(TicketConsumer.class);

    @KafkaListener(topics = "tickets", groupId = "group_id")
    public void consume(
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            OrderAcceptedEvent message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s %s", key, message.getId()));

        orderService.approveOrder(message.getId());
    }
}
