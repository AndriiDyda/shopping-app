package com.andrii.dd.orderservice.service;

import com.andrii.dd.orderservice.dto.OrderItemsDto;
import com.andrii.dd.orderservice.dto.OrderRequest;
import com.andrii.dd.orderservice.model.Order;
import com.andrii.dd.orderservice.model.OrderItems;
import com.andrii.dd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItemsList(orderRequest.getOrderItemsDtoList()
                .stream()
                .map(this::mapDto)
                .toList());

        orderRepository.save(order);
        return "Success";
    }

    private OrderItems mapDto(OrderItemsDto orderItemsDto) {
        return OrderItems.builder()
                .price(orderItemsDto.getPrice())
                .skuCode(orderItemsDto.getSkuCode())
                .quantity(orderItemsDto.getQuantity())
                .build();
    }
}
