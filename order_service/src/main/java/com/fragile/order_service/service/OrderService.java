package com.fragile.order_service.service;

import com.fragile.order_service.dto.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest request);

}