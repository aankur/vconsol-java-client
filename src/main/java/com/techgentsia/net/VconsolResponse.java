package com.techgentsia.net;

import org.apache.hc.core5.http.message.HeaderGroup;

public class VconsolResponse extends AbstractVconsolResponse<String> {
  public VconsolResponse(int code, HeaderGroup headers, String body) {
    super(code, headers, body);
  }
}
