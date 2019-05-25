package com.product.domain.repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.product.domain.Product;
import java.io.IOException;
import java.util.ArrayList;
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

  @Inject
  ObjectMapper objectMapper;

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
    log.info("Getting all products");
    Collection<Product> products = new ArrayList<>();
    MongoCursor<Document> iterator = productCollection.find().iterator();
    try {
      while (iterator.hasNext()) {
        String json = iterator.next().toJson();
        Product product = objectMapper.readValue(json, Product.class);
        products.add(product);
      }
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      iterator.close();
    }
    return products;
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