package com.sangboak.micro.order.repository;

import com.sangboak.micro.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
