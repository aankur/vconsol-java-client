package com.techgentsia.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

public abstract class ApiRequestParams {
  public static Map<String, Object> paramsToMap(ApiRequestParams params) {
    if (params == null) {
      return null;
    }
    return params.toMap();
  }

  public Map<String, Object> toMap() {
    try {
      return ApiResource.INTERNAL_OBJECT_MAPPER.readValue(ApiResource.INTERNAL_OBJECT_MAPPER.writeValueAsString(this), new TypeReference<Map<String, Object>>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
