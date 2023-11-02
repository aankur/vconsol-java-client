package com.techgentsia.net;

public abstract class VconsolResponseHandlerOptions {

  public abstract String getApiBase();

  public abstract String getApiKey();

  public abstract String getApiSecret();

  public abstract int getConnectTimeoutMs();

  public abstract int getReadTimeoutMs();
}
