package com.fragile.inventory_service.service;

import com.fragile.inventory_service.dto.InventoryResponseDto;
import com.fragile.inventory_service.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDto> isInStock(List<String> skuCode);
}