package com.techgentsia.model;

import com.techgentsia.net.VconsolResponse;

public abstract class VconsolObject implements VconsolObjectInterface {
  private transient VconsolResponse lastResponse;

  @Override
  public VconsolResponse getLastResponse() {
    return lastResponse;
  }

  @Override
  public void setLastResponse(VconsolResponse response) {
    this.lastResponse = response;
  }
}
