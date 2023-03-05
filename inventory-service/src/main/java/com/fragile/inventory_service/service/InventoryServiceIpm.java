package com.fragile.inventory_service.service;

import com.fragile.inventory_service.dto.InventoryResponseDto;
import com.fragile.inventory_service.model.Inventory;
import com.fragile.inventory_service.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceIpm implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream().map(inventory -> InventoryResponseDto
                        .builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build())
                .toList();

    }

}
