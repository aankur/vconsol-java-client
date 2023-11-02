package com.techgentsia.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.techgentsia.Vconsol;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class CustomOffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

  static private final DateTimeFormatter format = new DateTimeFormatterBuilder()
    .appendOptional(DateTimeFormatter.ofPattern(Vconsol.ISO_8601_TIMESTAMP_PATTERN))
    .appendOptional(DateTimeFormatter.ofPattern(Vconsol.ISO_8601_TIMESTAMP_MS_PATTERN))
    .appendOptional(DateTimeFormatter.ofPattern(Vconsol.ISO_8601_TIMESTAMP_ERROR_PATTERN))
    .toFormatter();

  @SuppressWarnings("unused")
  public CustomOffsetDateTimeDeserializer() {
    this(OffsetDateTime.class);
  }

  protected CustomOffsetDateTimeDeserializer(Class<?> c) {
    super(c);
  }

  public static OffsetDateTime parse(String dateTime) {
    return OffsetDateTime.parse(dateTime, format);
  }

  @Override
  public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jsonParser.getCodec();
    JsonNode node = oc.readTree(jsonParser);
    return parse(node.textValue());
  }
}