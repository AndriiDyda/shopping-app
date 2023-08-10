package com.andrii.dd.orderservice.service;

import com.andrii.dd.orderservice.config.WebClientConfig;
import com.andrii.dd.orderservice.dto.InventoryResponse;
import com.andrii.dd.orderservice.dto.OrderItemsDto;
import com.andrii.dd.orderservice.dto.OrderRequest;
import com.andrii.dd.orderservice.model.Order;
import com.andrii.dd.orderservice.model.OrderItems;
import com.andrii.dd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItemsList(orderRequest.getOrderItemsDtoList()
                .stream()
                .map(this::mapDto)
                .toList());

        List<String> skuCodes = order.getOrderItemsList().stream()
                .map(OrderItems::getSkuCode)
                .toList();
        InventoryResponse result [] = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsAvailable = Arrays.stream(result).allMatch(InventoryResponse::isAvailable);
        if (allProductsAvailable){
            orderRepository.save(order);
        } else {
            throw new RuntimeException("The product is not available");
        }
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
