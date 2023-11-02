package com.techgentsia.net;

import lombok.experimental.Accessors;
import org.apache.hc.core5.http.message.HeaderGroup;

import static java.util.Objects.requireNonNull;

@Accessors(fluent = true)
abstract class AbstractVconsolResponse<T> {
  int code;
  HeaderGroup headers;
  T body;

  protected AbstractVconsolResponse(int code, HeaderGroup headers, T body) {
    requireNonNull(headers);
    requireNonNull(body);

    this.code = code;
    this.headers = headers;
    this.body = body;
  }

  public final int code() {
    return this.code;
  }

  public final HeaderGroup headers() {
    return this.headers;
  }

  public final T body() {
    return this.body;
  }
}
