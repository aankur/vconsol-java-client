package com.techgentsia.exception;

import com.techgentsia.model.VconsolError;
import lombok.Data;
import lombok.Getter;

@Data
public class VconsolException extends RuntimeException {
  @Getter
  transient VconsolError vconsolError;
  String message;
  Integer statusCode;

  public VconsolException(VconsolError vconsolError) {
    super(vconsolError.getMessage());
    this.vconsolError = vconsolError;
    this.statusCode = vconsolError.getStatus();
    this.message = vconsolError.getMessage();
  }

  public VconsolException(String message, Integer statusCode, Throwable e) {
    super(message, e);
    this.message = message;
    this.statusCode = statusCode;
  }

  public VconsolException(String message, Integer statusCode) {
    this(message, statusCode, null);
  }

  public VconsolException(String message) {
    this(message, null, null);
  }

  public VconsolException(Throwable e) {
    this(e.getMessage(), null, e);
  }
}
