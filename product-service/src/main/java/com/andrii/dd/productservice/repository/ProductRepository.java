package com.andrii.dd.productservice.repository;

import com.andrii.dd.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
