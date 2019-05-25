package com.product.domain.event;

import java.util.HashMap;
import java.util.Map;

public class CrmEvent {

  private String type;

  private Map<String, Object> data = new HashMap<>();

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "CrmEvent{" +
        "type='" + type + '\'' +
        ", data=" + data +
        '}';
  }

}