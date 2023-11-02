package com.techgentsia.param.user;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class UserCreateParam extends ApiRequestParams {
  String email;
  String firstName;
  String lastName;

  @Builder.Default
  String password = UUID.randomUUID().toString();
}
