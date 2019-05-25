package com.product.domain.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.product.domain.Product;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MongoProductRepository implements ProductRepository {

  private final Logger log = LoggerFactory.getLogger(MongoProductRepository.class);

  @Inject
  MongoDatabase mongoDatabase;

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
    log.info("Saving {}", product);
    MongoCollection<Document> collection = mongoDatabase.getCollection("product");
    collection.insertOne(product.toDocument());
    return product;
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