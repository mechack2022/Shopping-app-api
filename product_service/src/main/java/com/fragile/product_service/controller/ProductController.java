package com.fragile.product_service.controller;

import com.fragile.product_service.dto.ProductRequest;
import com.fragile.product_service.dto.ProductResponse;
import com.fragile.product_service.service.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductServices productServices;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productServices.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts(){
        return productServices.getAllProducts();
    }
}
