package com.andrii.dd.inventoryservice.service;

import com.andrii.dd.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean findBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
