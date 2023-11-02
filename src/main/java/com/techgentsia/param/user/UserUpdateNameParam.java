package com.techgentsia.param.user;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserUpdateNameParam extends ApiRequestParams {
  String firstName;
  String lastName;
}
