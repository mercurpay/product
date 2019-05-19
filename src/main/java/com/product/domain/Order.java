package com.product.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Order {

  private String id;

  private BigDecimal value;

  private String customerId;

  private String productId;

  private Set<Event> events = new HashSet<>();

  public Order addEvent(Event event) {
    this.events.add(event);
    return this;
  }

}