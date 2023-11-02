package com.techgentsia.net;

import com.techgentsia.Vconsol;

public class DefaultVconsolResponseHandlerOptions extends VconsolResponseHandlerOptions {
  public static DefaultVconsolResponseHandlerOptions INSTANCE = new DefaultVconsolResponseHandlerOptions();

  @Override
  public String getApiBase() {
    return Vconsol.getApiBase();
  }

  @Override
  public String getApiKey() {
    return Vconsol.getApiKey();
  }

  @Override
  public String getApiSecret() {
    return Vconsol.getApiSecret();
  }

  @Override
  public int getConnectTimeoutMs() {
    return Vconsol.getConnectTimeoutMs();
  }

  @Override
  public int getReadTimeoutMs() {
    return Vconsol.getReadTimeoutMs();
  }
}
