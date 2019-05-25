package com.product.domain.service;

import com.product.domain.Order;
import com.product.domain.Product;
import com.product.domain.repository.ProductRepository;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProductService {

  private final Logger log = LoggerFactory.getLogger(ProductService.class);

  @Inject
  ProductRepository productRepository;

  public void analyzeOrder(final Order order) {
    log.info("Received {} to be analyzed", order);
    productRepository.deleteAll();
  }

  public Product get(String id) {
    log.info("Getting product {}", id);
    return productRepository.findOne(id);
  }

  public Collection<Product> getAll() {
    log.info("Getting all products");
    return productRepository.findAll();
  }

  public Product create(Product product) {
    log.info("Creating product {}", product);
    return productRepository.create(product);
  }

  public Product update(String id, Product product) {
    log.info("Creating product {}", id);
    return productRepository.update(id, product);
  }

  public void removeAll() {
    log.info("Removing all products");
    productRepository.deleteAll();
  }

  public void remove(String id) {
    log.info("Deleting product {}", id);
    productRepository.deleteOne(id);
  }

}