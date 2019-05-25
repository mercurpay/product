package com.product.domain.event;

public class AnalyzeOrderEvent {

  private String orderId;

  private String productId;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  @Override
  public String toString() {
    return "AnalyzeOrderEvent{" +
        "orderId='" + orderId + '\'' +
        ", productId='" + productId + '\'' +
        '}';
  }

}