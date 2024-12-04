package com.techgentsia.param.user;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Builder
@Value
public class UserCreateParam extends ApiRequestParams {
  String email;
  String firstName;
  String lastName;

  @Builder.Default
  String password = UUID.randomUUID().toString();

  String ssoLead;

  String phone;

  Map<String,String> attributes;

  public static class UserCreateParamBuilder {
    Map<String,String> attributes = new LinkedHashMap<>();
    public UserCreateParamBuilder addAttribute(String key, String value) {
      this.attributes.put(key, value);
      return this;
    }
  }
}
