package com.techgentsia.net;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techgentsia.model.VconsolActiveObject;
import com.techgentsia.model.VconsolObject;
import com.techgentsia.utils.VconsolObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiResource extends VconsolObject implements VconsolActiveObject {
  public static final ObjectMapper INTERNAL_OBJECT_MAPPER = VconsolObjectMapper.objectMapper();
  public static final Charset CHARSET = StandardCharsets.UTF_8;
  @Setter
  @Getter
  ResponseHandler responseHandler;

  public static String urlEncode(String str) {
    if (str == null) {
      return null;
    }

    try {
      return URLEncoder.encode(str, CHARSET.name()).replaceAll("%5B", "[").replaceAll("%5D", "]");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("UTF-8 is unknown");
    }
  }

  public static String base64Encode(String str) {
    if (str == null) {
      return null;
    }
    return Base64.getEncoder().encodeToString(str.getBytes(CHARSET));
  }

  public static <T> TypeReference<T> getTypeReference(Class<T> tClass) {
    return new TypeReference<T>() {
      @Override
      public Type getType() {
        return tClass;
      }
    };
  }

  public enum RequestMethod {
    GET(HttpGet.METHOD_NAME),
    PUT(HttpPut.METHOD_NAME),
    POST(HttpPost.METHOD_NAME),
    DELETE(HttpDelete.METHOD_NAME);
    private final String text;

    RequestMethod(final String text) {
      this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
      return text;
    }
  }

}
