package com.product.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.domain.Order;
import com.product.domain.ProductService;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Alternative
@Priority(1)
@Singleton
public class DispatcherConfiguration implements Dispatcher {

  private final Logger log = LoggerFactory.getLogger(DispatcherConfiguration.class);

  @Inject
  Connection connection;

  @Inject
  ObjectMapper objectMapper;

  @Inject
  ProductService productService;

  private final Dispatcher dispatcher;

  DispatcherConfiguration() {
    String topic = "product-analyze-topic";
    log.info("Configuring Dispatcher, Topic=[{}]", topic);

    dispatcher = connection.createDispatcher(message -> {
      String data = new String(message.getData(), StandardCharsets.UTF_8);
      try {
        log.info("Received message $data");
        Order order = objectMapper.readValue(data, Order.class);
        productService.analyzeOrder(order);
      } catch (Exception e) {
        log.error("Error to analyze {}", data, e);
      }
    });

    dispatcher.subscribe(topic);
    log.info("Configured Dispatcher, Topic=[{}]", topic);
  }

  @Override
  public Dispatcher subscribe(String s) {
    return dispatcher.subscribe(s);
  }

  @Override
  public Dispatcher subscribe(String s, String s1) {
    return dispatcher.subscribe(s, s1);
  }

  @Override
  public Dispatcher unsubscribe(String s) {
    return dispatcher.unsubscribe(s);
  }

  @Override
  public Dispatcher unsubscribe(String s, int i) {
    return dispatcher.unsubscribe(s, i);
  }

  @Override
  public void setPendingLimits(long l, long l1) {
    dispatcher.setPendingLimits(l, l1);
  }

  @Override
  public long getPendingMessageLimit() {
    return dispatcher.getPendingMessageLimit();
  }

  @Override
  public long getPendingByteLimit() {
    return dispatcher.getPendingByteLimit();
  }

  @Override
  public long getPendingMessageCount() {
    return dispatcher.getPendingMessageCount();
  }

  @Override
  public long getPendingByteCount() {
    return dispatcher.getPendingByteCount();
  }

  @Override
  public long getDeliveredCount() {
    return dispatcher.getDeliveredCount();
  }

  @Override
  public long getDroppedCount() {
    return dispatcher.getDroppedCount();
  }

  @Override
  public void clearDroppedCount() {
    dispatcher.clearDroppedCount();
  }

  @Override
  public boolean isActive() {
    return dispatcher.isActive();
  }

  @Override
  public CompletableFuture<Boolean> drain(Duration duration) throws InterruptedException {
    return dispatcher.drain(duration);
  }

}