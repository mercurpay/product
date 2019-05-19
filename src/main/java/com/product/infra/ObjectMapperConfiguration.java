package com.product.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Singleton;

@Singleton
public class ObjectMapperConfiguration extends ObjectMapper {

  ObjectMapperConfiguration() {
    super();
  }

}