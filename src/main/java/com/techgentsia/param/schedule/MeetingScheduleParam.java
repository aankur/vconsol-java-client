package com.techgentsia.param.schedule;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Builder
@Value
public class MeetingScheduleParam extends ApiRequestParams {
  String title;
  String description;
  Boolean audioMuted;

  OffsetDateTime startTime;
  OffsetDateTime endTime;
  Boolean needsContentPermission;
  Boolean passiveEnabled;

  @Builder.Default
  String timeZone = "Asia/Kolkata";
  Boolean passiveWaitingRoom;
  Boolean waitingRoom;
}
