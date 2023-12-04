package com.techgentsia.model;

import com.techgentsia.exception.VconsolException;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

@AllArgsConstructor
@Value
public class MeetingUrl {
  String url;
  String preAuthToken;
  Map<String, Object> queryParams;

  public String getUrl() {
    if (queryParams == null || queryParams.isEmpty()) {
      return url;
    }
    try {
      URIBuilder uriBuilder = new URIBuilder(url);
      for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
        uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
      }
      return uriBuilder.toString();
    } catch (URISyntaxException e) {
      throw new VconsolException(e);
    }
  }
}
