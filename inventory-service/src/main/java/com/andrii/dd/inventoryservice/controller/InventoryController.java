package com.andrii.dd.inventoryservice.controller;

import com.andrii.dd.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInInventory(@PathVariable("sku-code") String skuCode){
        return inventoryService.findBySkuCode(skuCode);
    }
}
