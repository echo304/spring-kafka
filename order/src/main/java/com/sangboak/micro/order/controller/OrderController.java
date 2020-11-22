package com.sangboak.micro.order.controller;

import com.sangboak.micro.order.entity.Order;
import com.sangboak.micro.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    final private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{orderId}")
    public String getOrder(@PathVariable Long orderId) {
        return "HelloWorld";
    }

    @PostMapping("/orders")
    public Long createOrder() {
        return orderService.createOrder();
    }
}
