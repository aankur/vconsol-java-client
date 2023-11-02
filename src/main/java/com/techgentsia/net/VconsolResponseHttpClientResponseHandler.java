package com.techgentsia.net;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.HeaderGroup;

import java.io.IOException;

public class VconsolResponseHttpClientResponseHandler implements HttpClientResponseHandler<VconsolResponse> {
  @Override
  public VconsolResponse handleResponse(ClassicHttpResponse response) {
    try {
      HeaderGroup headerGroup = new HeaderGroup();
      headerGroup.setHeaders(response.getHeaders());
      return new VconsolResponse(response.getCode(),headerGroup, EntityUtils.toString(response.getEntity(), ApiResource.CHARSET));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
