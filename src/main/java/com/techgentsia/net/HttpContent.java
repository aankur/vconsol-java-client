package com.techgentsia.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Map;

@Value
@Accessors(fluent = true)
public class HttpContent {

  String content;

  String contentType;

  private HttpContent(String content, String contentType) {
    this.content = content;
    if (contentType != null) {
      this.contentType = contentType;
    } else {
      this.contentType = "application/json";
    }
  }

  public static HttpContent buildHttpContent(Map<String, Object> params) {
    if (params == null) {
      return new HttpContent(null, "application/json");
    }
    try {
      return new HttpContent(ApiResource.INTERNAL_OBJECT_MAPPER.writeValueAsString(params), "application/json");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
