package com.techgentsia.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techgentsia.Vconsol;

import java.util.HashMap;
import java.util.Map;

public abstract class VconsolHttpClient {

  protected VconsolHttpClient() {
  }

  protected static String buildUserAgentString() {
    String userAgent = String.format("Vconsol/v1 JavaBindings/%s", Vconsol.VERSION);

    if (Vconsol.getAppInfo() != null) {
      userAgent += " " + formatAppInfo(Vconsol.getAppInfo());
    }

    return userAgent;
  }

  private static String formatAppInfo(Map<String, String> info) {
    String str = info.get("name");

    if (info.get("version") != null) {
      str += String.format("/%s", info.get("version"));
    }

    if (info.get("url") != null) {
      str += String.format(" (%s)", info.get("url"));
    }

    return str;
  }

  protected static String buildXVconsolClientUserAgentString() {
    String[] propertyNames = {
      "os.name",
      "os.version",
      "os.arch",
      "java.version",
      "java.vendor",
      "java.vm.version",
      "java.vm.vendor"
    };

    Map<String, String> propertyMap = new HashMap<>();
    for (String propertyName : propertyNames) {
      propertyMap.put(propertyName, System.getProperty(propertyName));
    }
    propertyMap.put("bindings.version", Vconsol.VERSION);
    propertyMap.put("lang", "Java");
    propertyMap.put("publisher", "Vconsol");
    if (Vconsol.getAppInfo() != null) {
      try {
        propertyMap.put("application", ApiResource.INTERNAL_OBJECT_MAPPER.writeValueAsString(Vconsol.getAppInfo()));
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      return ApiResource.INTERNAL_OBJECT_MAPPER.writeValueAsString(propertyMap);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public abstract VconsolResponse request(VconsolRequest request);


}
