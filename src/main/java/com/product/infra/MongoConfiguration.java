package com.product.infra;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MongoConfiguration {

  @Inject
  @ConfigProperty(name = "mongo.uri")
  String mongoUri;

  @Inject
  @ConfigProperty(name = "mongo.database")
  String mongoDatabase;

  @Produces
  @Singleton
  MongoClient createMongoClient() {
    return MongoClients.create(mongoUri);
  }

  @Produces
  @Singleton
  MongoDatabase createMongoDatabase(MongoClient mongoClient) {
    return mongoClient.getDatabase(mongoDatabase);
  }

}