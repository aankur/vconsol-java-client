package com.techgentsia.service;

import com.techgentsia.model.User;
import com.techgentsia.net.*;
import com.techgentsia.param.user.UserCreateParam;
import com.techgentsia.param.user.UserRetrieveParam;

public class UserService extends ApiService {
  public UserService(ResponseHandler responseHandler) {
    super(responseHandler);
  }

  public User retrieveUser(UserRetrieveParam userRetrieveParam) {
    return retrieveUser(userRetrieveParam, null);
  }

  public User retrieveUser(UserRetrieveParam userRetrieveParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/user/%s";
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.GET,
        String.format(path, ApiResource.urlEncode(userRetrieveParam.getEmail())),
        null,
        ApiResource.getTypeReference(User.class),
        requestOptions);
  }


  public User createUser(UserCreateParam userCreateParam) {
    return createUser(userCreateParam, null);
  }

  public User createUser(UserCreateParam userCreateParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/user";
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.POST,
        path,
        ApiRequestParams.paramsToMap(userCreateParam),
        ApiResource.getTypeReference(User.class),
        requestOptions);
  }

  public User createUser2(UserCreateParam userCreateParam) {
    return createUser2(userCreateParam, null);
  }

  public User createUser2(UserCreateParam userCreateParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/user2";
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.POST,
        path,
        ApiRequestParams.paramsToMap(userCreateParam),
        ApiResource.getTypeReference(User.class),
        requestOptions);
  }
}
