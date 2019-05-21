package com.product.domain.repository;

import com.product.domain.Product;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MongoProductRepository implements ProductRepository {

  @Override
  public Product findOne(String id) {
    // TODO
    return null;
  }

  @Override
  public Collection<Product> findAll() {
    // TODO
    return null;
  }

  @Override
  public Product create(Product product) {
    // TODO
    return null;
  }

  @Override
  public void deleteAll() {
    // TODO
  }

  @Override
  public void deleteOne(String id) {
    // TODO
  }

}