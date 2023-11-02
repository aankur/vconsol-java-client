package com.techgentsia;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class Vconsol {

  public static final String ISO_8601_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
  public static final String ISO_8601_TIMESTAMP_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
  public static final String ISO_8601_TIMESTAMP_ERROR_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  public static final String VERSION = "1.0.0";

  public static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
  public static final int DEFAULT_READ_TIMEOUT = 90 * 1000;

  public static final String LIVE_API_BASE = "https://business.vconsol.com";
  @Getter
  public static volatile String apiKey;
  @Getter
  public static volatile String apiSecret;
  @Getter
  private static volatile String apiBase = LIVE_API_BASE;
  @Getter
  private static volatile int connectTimeoutMs = DEFAULT_CONNECT_TIMEOUT;
  @Getter
  private static volatile int readTimeoutMs = DEFAULT_READ_TIMEOUT;
  private static volatile Map<String, String> appInfo = null;

  public static void overrideApiBase(String apiBase) {
    Vconsol.apiBase = apiBase;
  }

  public static void overrideApiKey(String apiKey) {
    Vconsol.apiKey = apiKey;
  }

  public static void overrideApiSecret(String apiSecret) {
    Vconsol.apiSecret = apiSecret;
  }

  public static void overrideReadTimeoutMs(int readTimeoutMs) {
    Vconsol.readTimeoutMs = readTimeoutMs;
  }

  public static void overrideConnectTimeoutMs(int connectTimeoutMs) {
    Vconsol.connectTimeoutMs = connectTimeoutMs;
  }

  public static void setAppInfo(String name, String version) {
    setAppInfo(name, version, null);
  }

  /**
   * Sets information about your application. The information is passed along to Vconsol.
   *
   * @param name      Name of your application (e.g. "NameOfYourApp")
   * @param version   Version of your application (e.g. "2.4.6")
   * @param url       Website for your application (e.g. "https://yourapp.io")
   */
  public static void setAppInfo(String name, String version, String url) {
    if (appInfo == null) {
      appInfo = new HashMap<String, String>();
    }

    appInfo.put("name", name);
    appInfo.put("version", version);
    appInfo.put("url", url);
  }

  public static Map<String, String> getAppInfo() {
    return appInfo;
  }

  public static void setAppInfo(String name) {
    setAppInfo(name, null, null);
  }

}
