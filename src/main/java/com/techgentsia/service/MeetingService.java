package com.techgentsia.service;

import com.techgentsia.model.MeetingUrl;
import com.techgentsia.model.RawObject;
import com.techgentsia.net.*;
import com.techgentsia.param.meeting.JoinMeetingParam;
import com.techgentsia.param.meeting.MeetingUrlQueryParams;

public class MeetingService extends ApiService {

  final String baseUrl;

  public MeetingService(String baseUrl, ResponseHandler responseHandler) {
    super(responseHandler);
    this.baseUrl = baseUrl;
  }

  String getBaseURl(RequestOptions requestOptions) {
    if (requestOptions != null && requestOptions.getBaseUrl() != null) {
      return requestOptions.getBaseUrl();
    }
    return baseUrl;
  }

  private RawObject join(String path, JoinMeetingParam joinMeetingParam, RequestOptions requestOptions) {
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.POST,
        String.format(path, ApiResource.urlEncode(joinMeetingParam.getMeetingID())),
        ApiRequestParams.paramsToMap(joinMeetingParam),
        ApiResource.getTypeReference(RawObject.class),
        requestOptions);
  }

  private MeetingUrl generateMeetingURL(String response, MeetingUrlQueryParams meetingUrlQueryParams, RequestOptions requestOptions) {
    final String preAuthToken = ApiResource.urlEncode(ApiResource.base64Encode(response));
    return new MeetingUrl(
      String.format("%s/join/pre-auth/%s", getBaseURl(requestOptions), preAuthToken), preAuthToken,
      meetingUrlQueryParams == null ? null : ApiRequestParams.paramsToMap(meetingUrlQueryParams)
    );
  }

  public MeetingUrl joinAsModerator(JoinMeetingParam joinMeetingParam) {
    return joinAsModerator(joinMeetingParam, null);
  }

  public MeetingUrl joinAsModerator(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams) {
    return joinAsModerator(joinMeetingParam, meetingUrlQueryParams, null);
  }

  public MeetingUrl joinAsModerator(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams, RequestOptions requestOptions) {
    final String path = "/external/api/v1/pre-auth/meeting/moderator/%s";
    RawObject rawObject = join(String.format(path, ApiResource.urlEncode(joinMeetingParam.getMeetingID())), joinMeetingParam, requestOptions);
    return generateMeetingURL(rawObject.getValue(), meetingUrlQueryParams, requestOptions);
  }

  public MeetingUrl joinAsActive(JoinMeetingParam joinMeetingParam) {
    return joinAsActive(joinMeetingParam, null);
  }

  public MeetingUrl joinAsActive(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams) {
    return joinAsActive(joinMeetingParam, meetingUrlQueryParams, null);
  }

  public MeetingUrl joinAsActive(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams, RequestOptions requestOptions) {
    final String path = "/external/api/v1/pre-auth/meeting/join/%s";
    RawObject rawObject = join(String.format(path, ApiResource.urlEncode(joinMeetingParam.getMeetingID())), joinMeetingParam, requestOptions);
    return generateMeetingURL(rawObject.getValue(), meetingUrlQueryParams, requestOptions);
  }

  public MeetingUrl joinAsPassive(JoinMeetingParam joinMeetingParam) {
    return joinAsPassive(joinMeetingParam, null);
  }

  public MeetingUrl joinAsPassive(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams) {
    return joinAsPassive(joinMeetingParam, meetingUrlQueryParams, null);
  }

  public MeetingUrl joinAsPassive(JoinMeetingParam joinMeetingParam, MeetingUrlQueryParams meetingUrlQueryParams, RequestOptions requestOptions) {
    final String path = "/external/api/v1/pre-auth/meeting/viewer/%s";
    RawObject rawObject = join(String.format(path, ApiResource.urlEncode(joinMeetingParam.getMeetingID())), joinMeetingParam, requestOptions);
    return generateMeetingURL(rawObject.getValue(), meetingUrlQueryParams, requestOptions);
  }

}
