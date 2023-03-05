package com.fragile.product_service.service;

import com.fragile.product_service.dto.ProductRequest;
import com.fragile.product_service.dto.ProductResponse;
import com.fragile.product_service.model.Product;
import com.fragile.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {

    private final ProductRepository repository;
    private final ProductRepository productRepository;


    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product with id : {} saved successfully", product.getProductId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product>  products = productRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return  products.stream().map(product -> mapper.map(product, ProductResponse.class)).collect(Collectors.toList());
    }



}

