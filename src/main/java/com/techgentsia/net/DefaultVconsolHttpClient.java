package com.techgentsia.net;

import com.techgentsia.exception.VconsolException;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.HeaderGroup;
import org.apache.hc.core5.util.Args;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class DefaultVconsolHttpClient extends VconsolHttpClient {

  public DefaultVconsolHttpClient() {
    super();
  }

  static HeaderGroup getHeaders(VconsolRequest request) {
    request.headers().addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, request.content().contentType()));
    request.headers().addHeader(new BasicHeader(HttpHeaders.USER_AGENT, buildUserAgentString()));
    request.headers().addHeader(new BasicHeader(
      "X-Vconsol-Client-User-Agent", buildXVconsolClientUserAgentString()));

    return request.headers();
  }

  public static HttpUriRequest create(ApiResource.RequestMethod method, URI uri, HttpContent httpContent) {
    String content = (httpContent == null) ? null : httpContent.content() == null ? null : httpContent.content();
    switch (Args.notNull(method, "method")) {
      case DELETE:
        return new HttpDelete(uri);
      case GET:
        return new HttpGet(uri);
      case POST: {
        HttpPost post = new HttpPost(uri);
        if (content != null) {
          post.setEntity(new StringEntity(content));
        }
        return post;
      }
      case PUT: {
        HttpPut put = new HttpPut(uri);
        if (content != null) {
          put.setEntity(new StringEntity(content));
        }
        return put;
      }
      default:
        throw new IllegalArgumentException(method.toString());
    }
  }

  @Override
  public VconsolResponse request(VconsolRequest request) {
    ConnectionConfig connConfig = ConnectionConfig.custom()
      .setConnectTimeout(request.options().getConnectTimeoutMs(), TimeUnit.MILLISECONDS)
      .build();


    RequestConfig requestConfig = RequestConfig.custom()
      .setResponseTimeout(Timeout.ofMilliseconds(request.options().getReadTimeoutMs()))
      .build();

    BasicHttpClientConnectionManager cm = new BasicHttpClientConnectionManager();
    cm.setConnectionConfig(connConfig);

    try {
      HttpUriRequest httpRequest = create(request.method(), request.url().toURI(), request.content());
      for (Header entry : getHeaders(request).getHeaders()) {
        httpRequest.addHeader(entry);
      }
      try (CloseableHttpClient client = HttpClientBuilder.create()
        .setDefaultRequestConfig(requestConfig)
        .setConnectionManager(cm)
        .build()) {
        return client.execute(httpRequest, new VconsolResponseHttpClientResponseHandler());
      }
    } catch (URISyntaxException | IOException e) {
      throw new VconsolException(e);
    }
  }

}
