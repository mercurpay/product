package com.product.domain;

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

  @Override
  public HealthCheckResponse call() {
    log.info("Received a HealthCheck request");
    return HealthCheckResponse.builder()
        .name("NATS Dispatcher")
        .up()
        .build();
  }

}