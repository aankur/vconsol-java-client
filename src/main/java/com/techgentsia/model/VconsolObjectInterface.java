package com.techgentsia.model;

import com.techgentsia.net.VconsolResponse;

public interface VconsolObjectInterface {
  VconsolResponse getLastResponse();

  void setLastResponse(VconsolResponse response);
}
