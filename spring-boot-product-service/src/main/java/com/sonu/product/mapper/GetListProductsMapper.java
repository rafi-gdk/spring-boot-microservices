package com.sonu.product.mapper;


import com.sonu.product.model.Product;
import com.sonu.product.model.ProductRequest;
import com.sonu.product.model.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GetListProductsMapper {

    List<ProductResponse> getListProductResponse(List<Product> products);

    List<Product> getListProducts(List<ProductRequest> products);
}
