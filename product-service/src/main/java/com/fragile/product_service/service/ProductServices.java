package com.fragile.product_service.service;

import com.fragile.product_service.dto.ProductRequest;
import com.fragile.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductServices {

    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}

