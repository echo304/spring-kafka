package com.sangboak.micro.order.service;

import com.sangboak.micro.order.dto.OrderCreatedEvent;
import com.sangboak.micro.order.entity.Order;
import com.sangboak.micro.order.entity.OrderStatus;
import com.sangboak.micro.order.messaging.OrderProducer;
import com.sangboak.micro.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private OrderProducer orderProducer;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Long createOrder() {
        Order newOrder = new Order();
        newOrder.setStatus(OrderStatus.APPROVAL_PENDING);
        orderRepository.save(newOrder);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(newOrder.getId(), "menuName");
        orderProducer.sendMessage("ORDER_CREATED", orderCreatedEvent);

        return newOrder.getId();
    }

    @Transactional
    public Order approveOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.APPROVAL_CONFIRMED);
        orderRepository.save(order);

        return order;
    }
}
