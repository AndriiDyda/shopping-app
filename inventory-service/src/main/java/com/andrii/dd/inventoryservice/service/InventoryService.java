package com.andrii.dd.inventoryservice.service;

import com.andrii.dd.inventoryservice.dto.InventoryResponse;
import com.andrii.dd.inventoryservice.model.Inventory;
import com.andrii.dd.inventoryservice.repository.InventoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> findBySkuCode(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isAvailable(inventory.getQuantity() > 0)
                        .build())
                .toList();
    }
}
