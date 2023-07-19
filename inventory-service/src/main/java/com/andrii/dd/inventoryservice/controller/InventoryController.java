package com.andrii.dd.inventoryservice.controller;

import com.andrii.dd.inventoryservice.dto.InventoryResponse;
import com.andrii.dd.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInInventory(@RequestParam List<String> skuCode){
        return inventoryService.findBySkuCode(skuCode);
    }
}
