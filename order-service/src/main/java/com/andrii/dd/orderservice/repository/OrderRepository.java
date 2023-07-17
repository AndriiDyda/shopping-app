package com.andrii.dd.orderservice.repository;

import com.andrii.dd.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
