package com.product.domain;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProductService {

  private final Logger log = LoggerFactory.getLogger(ProductService.class);

  public void analyzeOrder(final Order order) {
    log.info("Received {} to be analyzed", order);
    // TODO
  }

}
