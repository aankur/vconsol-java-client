package com.techgentsia.net;

import com.techgentsia.Vconsol;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class RequestOptions {
  String baseUrl;
  String apiKey;
  String apiSecret;

  Integer connectTimeoutMs;
  Integer readTimeoutMs;

  public RequestOptions(String baseUrl, String apiKey, String apiSecret, Integer connectTimeoutMs, Integer readTimeoutMs) {
    this.baseUrl = Optional.ofNullable(baseUrl).orElse(Vconsol.getApiBase());
    this.apiKey = Optional.ofNullable(apiKey).orElse(Vconsol.getApiKey());
    this.apiSecret = Optional.ofNullable(apiSecret).orElse(Vconsol.getApiSecret());
    this.connectTimeoutMs = Optional.ofNullable(connectTimeoutMs).orElse(Vconsol.getConnectTimeoutMs());
    this.readTimeoutMs = Optional.ofNullable(readTimeoutMs).orElse(Vconsol.getReadTimeoutMs());
  }

  public static RequestOptions getDefault() {
    return new RequestOptions(null, null, null, null, null);
  }

  static RequestOptions merge(VconsolResponseHandlerOptions clientOptions, RequestOptions options) {
    if (options == null) {
      return new RequestOptions(
        clientOptions.getApiBase(),
        clientOptions.getApiKey(),
        clientOptions.getApiSecret(),
        clientOptions.getConnectTimeoutMs(),
        clientOptions.getReadTimeoutMs()
      );
    }
    return new RequestOptions(
      Optional.ofNullable(options.getBaseUrl()).orElse(clientOptions.getApiBase()),
      Optional.ofNullable(options.getApiKey()).orElse(clientOptions.getApiKey()),
      Optional.ofNullable(options.getApiSecret()).orElse(clientOptions.getApiSecret()),
      Optional.ofNullable(options.getConnectTimeoutMs()).orElse(clientOptions.getConnectTimeoutMs()),
      Optional.ofNullable(options.getReadTimeoutMs()).orElse(clientOptions.getReadTimeoutMs())
    );
  }

}
