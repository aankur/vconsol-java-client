package com.techgentsia.param.schedule;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DeleteMeetingScheduleParam extends ApiRequestParams {
  String meetingID;
}
