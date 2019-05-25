package com.product.domain.repository;

import com.product.domain.Product;
import java.util.Collection;

public interface ProductRepository {

  Product findOne(String id);

  Collection<Product> findAll();

  Product create(Product product);

  Product update(String id, Product product);

  void deleteAll();

  void deleteOne(String id);

}