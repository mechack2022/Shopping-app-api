package com.fragile.order_service.service;

import com.fragile.order_service.dto.OrderRequest;
import com.fragile.order_service.model.Order;
import com.fragile.order_service.model.OrderLineItem;
import com.fragile.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceIpm implements OrderService {
    final private OrderRepository orderRepository;


    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream().map(item -> new ModelMapper().map(item, OrderLineItem.class)).collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);

    }
}

