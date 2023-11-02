package com.techgentsia.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class VconsolError extends VconsolObject {
  OffsetDateTime timestamp;
  int status;
  String error;
  String message;
  String path;
}
