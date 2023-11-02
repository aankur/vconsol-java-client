package com.techgentsia.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.techgentsia.Vconsol;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CustomOffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {
  static private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(Vconsol.ISO_8601_TIMESTAMP_PATTERN);

  public CustomOffsetDateTimeSerializer() {
    super((OffsetDateTime.class));
  }

  protected CustomOffsetDateTimeSerializer(Class<OffsetDateTime> t) {
    super(t);
  }

  public static String format(OffsetDateTime dateTime) {
    return dateTime.format(dateTimeFormat);
  }

  @Override
  public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    if (offsetDateTime == null) {
      jsonGenerator.writeNull();
      return;
    }
    jsonGenerator.writeString(format(offsetDateTime));
  }
}
