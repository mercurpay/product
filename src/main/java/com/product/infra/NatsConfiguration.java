package com.product.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.domain.event.AnalyzeOrderEvent;
import com.product.domain.service.ProductService;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import io.nats.client.Options;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NatsConfiguration {

  private final Logger log = LoggerFactory.getLogger(NatsConfiguration.class);

  @Inject
  @ConfigProperty(name = "nats.user")
  String natsUser;

  @Inject
  @ConfigProperty(name = "nats.pass")
  String natsPass;

  @Inject
  @ConfigProperty(name = "nats.host")
  String natsHost;

  @Produces
  @Singleton
  Connection createConnection() throws IOException, InterruptedException {
    log.info("Host " + natsHost);
    log.info("User " + natsUser);
    log.info("Passsword " + natsPass);

    return Nats.connect(new Options.Builder()
        .connectionTimeout(Duration.ofSeconds(2))
        .pingInterval(Duration.ofSeconds(10))
        .reconnectWait(Duration.ofSeconds(1))
        .userInfo(natsUser, natsPass)
        .maxReconnects(-1)
        .reconnectBufferSize(-1)
        .connectionName(natsHost)
        .server(natsHost)
        .build());
  }

  @Produces
  @Singleton
  Dispatcher createDispatcher(Connection connection, ObjectMapper objectMapper,
      ProductService productService) {
    String topic = "product-analyze-topic";
    log.info("Configuring Dispatcher, Topic=[{}]", topic);

    Dispatcher dispatcher = connection.createDispatcher(message -> {
      String data = new String(message.getData(), StandardCharsets.UTF_8);
      try {
        log.info("Received message $data");
        AnalyzeOrderEvent analyzeOrderEvent = objectMapper.readValue(data, AnalyzeOrderEvent.class);
        productService.analyzeOrder(analyzeOrderEvent);
      } catch (Exception e) {
        log.error("Error to analyze {}", data, e);
      }
    });

    dispatcher.subscribe(topic);
    log.info("Configured Dispatcher, Topic=[{}]", topic);
    return dispatcher;
  }

}
