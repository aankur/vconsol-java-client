package com.techgentsia.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.techgentsia.utils.json.CustomOffsetDateTimeDeserializer;
import com.techgentsia.utils.json.CustomOffsetDateTimeSerializer;

import java.time.OffsetDateTime;

public class VconsolObjectMapper {
  public static ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    SimpleModule simpleOffsetDateTimeModule = new SimpleModule("OffsetDateTimeJsonModule", new Version(1, 0, 0, null, null, null));
    simpleOffsetDateTimeModule.addSerializer(OffsetDateTime.class, new CustomOffsetDateTimeSerializer());
    simpleOffsetDateTimeModule.addDeserializer(OffsetDateTime.class, new CustomOffsetDateTimeDeserializer());
    objectMapper.registerModule(simpleOffsetDateTimeModule);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }
}
