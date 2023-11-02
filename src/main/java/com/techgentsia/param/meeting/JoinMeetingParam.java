package com.techgentsia.param.meeting;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class JoinMeetingParam extends ApiRequestParams {
  String meetingID;
  String name;
  String password;
  String externalUserID;
  String externalUserParams;
}
