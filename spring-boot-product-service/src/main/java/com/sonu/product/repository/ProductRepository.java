package com.sonu.product.repository;

import com.sonu.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCustomerId(Integer customerId);
}
