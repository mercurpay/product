package com.product.domain.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.product.domain.Product;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MongoProductRepository implements ProductRepository {

  private final Logger log = LoggerFactory.getLogger(MongoProductRepository.class);
  private MongoCollection<Document> productCollection;

  @Inject
  MongoDatabase mongoDatabase;

  @PostConstruct
  public void postConstruct() {
    productCollection = mongoDatabase.getCollection("product");
  }

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
    productCollection.insertOne(product.toDocument());
    return product;
  }

  @Override
  public void deleteAll() {
    log.info("Deleting all products");
    productCollection.deleteMany(Filters.exists("_id"));
  }

  @Override
  public void deleteOne(String id) {
    log.info("Deleting product {}", id);
    productCollection.deleteOne(Filters.eq("_id", id));
  }

}