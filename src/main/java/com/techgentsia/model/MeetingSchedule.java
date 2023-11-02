package com.techgentsia.model;

import com.techgentsia.net.ApiResource;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MeetingSchedule extends ApiResource {

  String meetingID;
  String title;
  String description;
  boolean audioMuted;

  OffsetDateTime startAt;
  OffsetDateTime endAt;
  boolean needsContentPermission;
  boolean passiveEnabled;

  String timeZone;

  boolean waitingRoom;
  boolean passiveWaitingRoom;

  String meetingPassword;
  String passivePassword;
}
