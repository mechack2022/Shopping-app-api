package com.fragile.inventory_service.controller;


import com.fragile.inventory_service.dto.InventoryResponseDto;
import com.fragile.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    // because an order can contain more than one item, and
    // need to check the list for every item by their skuCode if available
//    to have something like this
//   webclient will construc the uri in thid form "http://localhost:9092/api/inventory?skuCode=phone&scuCode=aipod&scuCode=iphone_13"
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}