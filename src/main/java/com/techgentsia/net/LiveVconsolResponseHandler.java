package com.techgentsia.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.techgentsia.exception.VconsolException;
import com.techgentsia.model.VconsolActiveObject;
import com.techgentsia.model.VconsolError;
import com.techgentsia.model.VconsolObjectInterface;

import java.util.Map;

public class LiveVconsolResponseHandler implements ResponseHandler {

  VconsolResponseHandlerOptions options;
  VconsolHttpClient httpClient;

  public LiveVconsolResponseHandler() {
    this(null, null);
  }

  public LiveVconsolResponseHandler(VconsolHttpClient httpClient) {
    this(null, httpClient);
  }

  public LiveVconsolResponseHandler(VconsolResponseHandlerOptions options, VconsolHttpClient httpClient) {
    this.options = options != null ? options : DefaultVconsolResponseHandlerOptions.INSTANCE;
    this.httpClient = (httpClient != null) ? httpClient : buildDefaultHttpClient();
  }

  private static VconsolHttpClient buildDefaultHttpClient() {
    return new DefaultVconsolHttpClient();
  }

  public static <T> T getObjectFromJson(String jsonString, final TypeReference<T> typeReference) throws JsonProcessingException {
    return ApiResource.INTERNAL_OBJECT_MAPPER.readValue(jsonString, typeReference);
  }

  @Override
  public <T extends VconsolObjectInterface> T request(ApiResource.RequestMethod method, String path, Map<String, Object> bodyParams, TypeReference<T> typeReference, RequestOptions options) {
    String fullUrl = fullUrl(RequestOptions.merge(this.options, options), path);
    VconsolRequest request = new VconsolRequest(method, fullUrl, bodyParams, RequestOptions.merge(this.options, options));

    VconsolResponse response = httpClient.request(request);

    int responseCode = response.code();
    String responseBody = response.body();

    if (responseCode < 200 || responseCode >= 300) {
      handleError(response);
    }

    if (responseBody.trim().isEmpty() || typeReference == null) {
      return null;
    }

    T responseObject;
    try {
      responseObject = getObjectFromJson(responseBody, typeReference);
    } catch (JsonProcessingException e) {
      throw new VconsolException("Invalid JSON", responseCode, e);
    }
    if (responseObject instanceof VconsolActiveObject) {
      ((VconsolActiveObject) responseObject).setResponseHandler(this);
    }
    responseObject.setLastResponse(response);
    return responseObject;
  }

  private void handleError(VconsolResponse vconsolResponse) {
    try {
      VconsolError vconsolError = getObjectFromJson(vconsolResponse.body(), ApiResource.getTypeReference(VconsolError.class));
      throw new VconsolException(vconsolError);
    } catch (JsonProcessingException e) {
      throw new VconsolException("Invalid JSON", vconsolResponse.code(), e);
    }
  }

  private String fullUrl(RequestOptions options, String relativeUrl) {
    return String.format("%s%s", options.getBaseUrl(), relativeUrl);
  }
}
