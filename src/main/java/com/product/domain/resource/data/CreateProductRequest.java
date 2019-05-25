package com.product.domain.resource.data;

import com.product.domain.Product;
import java.util.UUID;

public class CreateProductRequest {

  private String name;

  private Integer quantity;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Product toProduct() {
    Product product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setName(getName());
    product.setQuantity(getQuantity());

    return product;
  }

}