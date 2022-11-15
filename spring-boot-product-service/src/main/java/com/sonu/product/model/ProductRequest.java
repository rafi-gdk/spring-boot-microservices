package com.sonu.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @Id
    private Integer productId;
    @NonNull
    private Integer customerId;
    private String productName;
    private Double productFare;
}
