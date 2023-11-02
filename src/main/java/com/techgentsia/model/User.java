package com.techgentsia.model;

import com.techgentsia.net.ApiRequestParams;
import com.techgentsia.net.ApiResource;
import com.techgentsia.net.RequestOptions;
import com.techgentsia.param.user.UserUpdateNameParam;
import lombok.Data;

@Data
public class User extends ApiResource {
  String id;
  String email;
  String firstName;
  String lastName;
  Boolean deleted;

  public User updateName(UserUpdateNameParam userUpdateNameParam) {
    return this.updateName(userUpdateNameParam, null);
  }

  public User updateName(UserUpdateNameParam userUpdateNameParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/user/%s";
    return getResponseHandler()
      .request(
        RequestMethod.PUT,
        String.format(path, ApiResource.urlEncode(email)),
        ApiRequestParams.paramsToMap(userUpdateNameParam),
        ApiResource.getTypeReference(User.class),
        requestOptions);
  }

  public User delete(RequestOptions requestOptions) {
    final String path = "/external/api/v1/user/%s";
    return getResponseHandler()
      .request(
        RequestMethod.DELETE,
        String.format(path, ApiResource.urlEncode(email)),
        null,
        ApiResource.getTypeReference(User.class),
        requestOptions);
  }

  public User delete() {
    return delete(null);
  }
}
