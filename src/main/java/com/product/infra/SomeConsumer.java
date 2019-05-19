package com.product.infra;

import io.nats.client.Dispatcher;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/test")
public class SomeConsumer {

  private final Logger log = LoggerFactory.getLogger(SomeConsumer.class);

  @Inject
  Dispatcher connection;

  @GET
  public String get() {
    log.info("Connection " + connection);
    return UUID.randomUUID().toString();
  }

}
