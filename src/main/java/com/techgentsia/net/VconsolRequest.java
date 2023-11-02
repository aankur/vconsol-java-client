package com.techgentsia.net;

import com.techgentsia.exception.AuthenticationException;
import com.techgentsia.utils.HmacSHA256;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.HeaderGroup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Value
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(fluent = true)
public class VconsolRequest {
  ApiResource.RequestMethod method;
  URL url;
  HeaderGroup headers;
  Map<String, Object> bodyParams;
  HttpContent content;
  RequestOptions options;


  public VconsolRequest(
    ApiResource.RequestMethod method,
    String url,
    Map<String, Object> bodyParams,
    RequestOptions options) {

    this.bodyParams = (bodyParams != null) ? Collections.unmodifiableMap(bodyParams) : null;
    this.options = (options != null) ? options : RequestOptions.getDefault();
    this.method = method;
    try {
      this.url = new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    this.content = buildHttpContent(bodyParams);
    this.headers = buildHeaders(this.content, this.options);
  }


  private static HttpContent buildHttpContent(Map<String, Object> params) {
    return HttpContent.buildHttpContent(params);
  }

  private static HeaderGroup buildHeaders(HttpContent content, RequestOptions options) {
    HeaderGroup headerGroup = new HeaderGroup();
    headerGroup.addHeader(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
    headerGroup.addHeader(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, ApiResource.CHARSET.name()));

    if (options.getApiKey() == null || options.getApiKey().trim().isEmpty()) {
      throw new AuthenticationException("API-KEY is not defined.");
    }

    if (options.getApiSecret() == null || options.getApiSecret().trim().isEmpty()) {
      throw new AuthenticationException("API-SECRET is not defined.");
    }
    headerGroup.addHeader(new BasicHeader("X-API-KEY",options.getApiKey()));
    headerGroup.addHeader(new BasicHeader("X-REQUEST-SIGNATURE", HmacSHA256.generateCurrentSignature(options.getApiSecret(), content == null ? "" : content.content() == null ? "" : content.content())));

    return headerGroup;
  }
}
