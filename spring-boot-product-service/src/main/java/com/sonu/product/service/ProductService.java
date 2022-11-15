package com.sonu.product.service;

import com.sonu.product.exception.RecordSaveFailed;
import com.sonu.product.exception.UserNotFoundException;
import com.sonu.product.mapper.GetListProductsMapper;
import com.sonu.product.model.Product;
import com.sonu.product.model.ProductRequest;
import com.sonu.product.model.ProductResponse;
import com.sonu.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GetListProductsMapper getListProductsMapper;

    public List<ProductResponse> getProducts(Integer id) {
        List<Product> allByCustomerId = productRepository.findAllByCustomerId(id);
        if (!CollectionUtils.isEmpty(allByCustomerId)) {
            return getListProductsMapper.getListProductResponse(allByCustomerId);
        }
        throw new UserNotFoundException("user not found");
    }

    public List<ProductResponse> addProducts(List<ProductRequest> products) {

        List<Product> listProducts = getListProductsMapper.getListProducts(products);
        List<Product> saveProducts = productRepository.saveAll(listProducts);
        if (!CollectionUtils.isEmpty(saveProducts)) {
            return getListProductsMapper.getListProductResponse(saveProducts);
        }
        throw new RecordSaveFailed("unable to save the customer");
    }
}
