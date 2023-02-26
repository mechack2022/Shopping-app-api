package com.fragile.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(value ="products")
public class Product {
    @Id
    private String productId;

    private String name;

    private String description;

    private BigDecimal price;


}

