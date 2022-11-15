package com.sonu.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    @Id
    private Integer productId;
    private String productName;
    private Double productFare;
}
