package com.sonu.customer.service;

import com.sonu.customer.exception.RecordSaveFailed;
import com.sonu.customer.exception.UserNotFoundException;
import com.sonu.customer.feign.FeignClientProduct;
import com.sonu.customer.model.*;
import com.sonu.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FeignClientProduct feignClientProduct;

    public CustomerResponse getCustomer(Integer id) {
        CustomerResponse response;
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()) {
            ResponseEntity<ProductResponse[]> responseEntity = restTemplate.getForEntity("http://product-service/product/get-products/" + id, ProductResponse[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful() && null != responseEntity.getBody()) {
                List<ProductResponse> products = Arrays.asList(responseEntity.getBody());
                Customer customer = byId.get();
                response = new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), products);
                return response;
            }
        }
        throw new UserNotFoundException("user not found");
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        CustomerResponse response;
        Customer customer = new Customer(request.getCustomerId(), request.getCustomerName());
        Customer saveCustomer = customerRepository.save(customer);
        if (null != saveCustomer.getCustomerId()) {
            request.getProducts().forEach(product -> product.setCustomerId(request.getCustomerId()));
            HttpEntity<List<Product>> productEntity = new HttpEntity<>(request.getProducts());
            ResponseEntity<ProductResponse[]> responseEntity = restTemplate.postForEntity("http://product-service/product/add-products", productEntity, ProductResponse[].class);
            if (responseEntity.getStatusCode().is2xxSuccessful() && null != responseEntity.getBody()) {
                List<ProductResponse> products = Arrays.asList(responseEntity.getBody());
                response = new CustomerResponse(saveCustomer.getCustomerId(), saveCustomer.getCustomerName(), products);
                return response;
            }
        }
        throw new RecordSaveFailed("unable to save the customer");
    }

    public CustomerResponse addFeignCustomer(CustomerRequest request) {
        CustomerResponse response;
        Customer customer = new Customer(request.getCustomerId(), request.getCustomerName());
        Customer saveCustomer = customerRepository.save(customer);
        if (null != saveCustomer.getCustomerId()) {
            request.getProducts().forEach(product -> product.setCustomerId(request.getCustomerId()));
            List<ProductResponse> productResponses = feignClientProduct.addProducts(request.getProducts());
            if (!productResponses.isEmpty()) {
                response = new CustomerResponse(saveCustomer.getCustomerId(), saveCustomer.getCustomerName(), productResponses);
                return response;
            }
        }
        throw new RecordSaveFailed("unable to save the customer");
    }
}
