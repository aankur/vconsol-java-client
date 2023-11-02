package com.techgentsia.model;

import com.techgentsia.net.ResponseHandler;

public interface VconsolActiveObject {
  void setResponseHandler(ResponseHandler responseHandler);

  default void trySetResponseGetter(Object object, ResponseHandler responseHandler) {
    if (object instanceof VconsolActiveObject) {
      ((VconsolActiveObject) object).setResponseHandler(responseHandler);
    }
  }
}
