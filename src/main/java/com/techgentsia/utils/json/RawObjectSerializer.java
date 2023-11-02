package com.techgentsia.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.techgentsia.model.RawObject;

import java.io.IOException;

public class RawObjectSerializer extends StdSerializer<RawObject> {

  public RawObjectSerializer() {
    super(RawObject.class);
  }

  @Override
  public void serialize(RawObject packet, JsonGenerator generator, SerializerProvider provider) throws IOException {
    if (packet.getValue() == null) {
      generator.writeNull();
    } else {
      generator.writeRawValue(packet.getValue());
    }
  }

  @Override
  public void serializeWithType(RawObject packet, JsonGenerator generator, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    serialize(packet, generator, provider);
  }
}
