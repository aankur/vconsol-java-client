package com.techgentsia.net;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techgentsia.model.VconsolObjectInterface;

import java.util.Map;

public interface ResponseHandler {
  <T extends VconsolObjectInterface> T request(
    ApiResource.RequestMethod method,
    String path,
    Map<String, Object> bodyParams,
    TypeReference<T> typeReference,
    RequestOptions requestOptions
  );
}
