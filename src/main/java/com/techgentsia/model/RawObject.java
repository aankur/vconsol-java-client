package com.techgentsia.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.techgentsia.net.VconsolResponse;
import com.techgentsia.utils.json.RawObjectDeserializer;
import com.techgentsia.utils.json.RawObjectSerializer;

@JsonSerialize(using = RawObjectSerializer.class)
@JsonDeserialize(using = RawObjectDeserializer.class)
public class RawObject implements VconsolObjectInterface {

  public final String value;
  public VconsolResponse vconsolResponse;

  public RawObject(String value) {
    this.value = value;
  }

  @Override
  public VconsolResponse getLastResponse() {
    return vconsolResponse;
  }

  @Override
  public void setLastResponse(VconsolResponse response) {
    this.vconsolResponse = response;
  }

  public String getValue() {
    return value;
  }
}