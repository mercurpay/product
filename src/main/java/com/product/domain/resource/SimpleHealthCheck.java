package com.product.domain.resource;

import com.mongodb.client.MongoDatabase;
import io.nats.client.Dispatcher;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Health
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {

  private final Logger log = LoggerFactory.getLogger(SimpleHealthCheck.class);

  @Inject
  Dispatcher dispatcher;

  @Inject
  MongoDatabase mongoDatabase;

  @Override
  public HealthCheckResponse call() {
    log.info("Received a HealthCheck request");
    return HealthCheckResponse.builder()
        .name("NATS, MongoDB")
        .up()
        .build();
  }

}