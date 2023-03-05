package com.fragile.order_service.service;

import com.fragile.order_service.dto.InventoryResponseDto;
import com.fragile.order_service.dto.OrderRequest;
import com.fragile.order_service.model.Order;
import com.fragile.order_service.model.OrderLineItem;
import com.fragile.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceIpm implements OrderService {
    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream().map(item -> new ModelMapper()
                        .map(item, OrderLineItem.class)).collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItem::getSkuCode).toList();
        // call the inventory service to know is product is available
        InventoryResponseDto[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allProductInStore = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponseDto::isInStock);

        if (allProductInStore) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product not in stock try again");
        }
    }
}

