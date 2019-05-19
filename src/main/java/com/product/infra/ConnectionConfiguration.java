package com.product.infra;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.client.Statistics;
import io.nats.client.Subscription;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import javax.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class ConnectionConfiguration implements Connection {

  @ConfigProperty(name = "nats.user")
  String natsUser;

  @ConfigProperty(name = "nats.pass")
  String natsPass;

  @ConfigProperty(name = "nats.host")
  String natsHost;

  private final Connection connection;

  ConnectionConfiguration() throws IOException, InterruptedException {
    this.connection = Nats.connect(new Options.Builder()
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

  @Override
  public void publish(String s, byte[] bytes) {
    connection.publish(s, bytes);
  }

  @Override
  public void publish(String s, String s1, byte[] bytes) {
    connection.publish(s, s1, bytes);
  }

  @Override
  public CompletableFuture<Message> request(String s, byte[] bytes) {
    return connection.request(s, bytes);
  }

  @Override
  public Message request(String s, byte[] bytes, Duration duration) throws InterruptedException {
    return connection.request(s, bytes, duration);
  }

  @Override
  public Subscription subscribe(String s) {
    return connection.subscribe(s);
  }

  @Override
  public Subscription subscribe(String s, String s1) {
    return connection.subscribe(s, s1);
  }

  @Override
  public Dispatcher createDispatcher(MessageHandler messageHandler) {
    return connection.createDispatcher(messageHandler);
  }

  @Override
  public void closeDispatcher(Dispatcher dispatcher) {
    connection.closeDispatcher(dispatcher);
  }

  @Override
  public void flush(Duration duration) throws TimeoutException, InterruptedException {
    connection.flush(duration);
  }

  @Override
  public CompletableFuture<Boolean> drain(Duration duration)
      throws TimeoutException, InterruptedException {
    return connection.drain(duration);
  }

  @Override
  public void close() throws InterruptedException {
    connection.close();
  }

  @Override
  public Status getStatus() {
    return connection.getStatus();
  }

  @Override
  public long getMaxPayload() {
    return connection.getMaxPayload();
  }

  @Override
  public Collection<String> getServers() {
    return connection.getServers();
  }

  @Override
  public Statistics getStatistics() {
    return connection.getStatistics();
  }

  @Override
  public Options getOptions() {
    return connection.getOptions();
  }

  @Override
  public String getConnectedUrl() {
    return connection.getConnectedUrl();
  }

  @Override
  public String getLastError() {
    return connection.getLastError();
  }

  @Override
  public String createInbox() {
    return connection.createInbox();
  }

}