package com.techgentsia.net;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class ApiService {
  @Getter(AccessLevel.PROTECTED)
  ResponseHandler responseHandler;

  protected ApiService(ResponseHandler responseHandler) {
    this.responseHandler = responseHandler;
  }
}
