package com.sonu.product.controller;

import com.sonu.product.model.ProductRequest;
import com.sonu.product.model.ProductResponse;
import com.sonu.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/get-products/{id}")
    public List<ProductResponse> getProducts(@PathVariable("id") Integer id) {
        return productService.getProducts(id);
    }

    @PostMapping(value = "/add-products")
    public List<ProductResponse> addProducts(@RequestBody List<ProductRequest> products) {
        return productService.addProducts(products);
    }

}
