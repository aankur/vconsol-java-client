package com.techgentsia.service;

import com.techgentsia.model.MeetingScheduleCollection;
import com.techgentsia.net.*;
import com.techgentsia.param.schedule.BreakoutScheduleParam;
import com.techgentsia.param.schedule.DeleteMeetingScheduleParam;
import com.techgentsia.param.schedule.MeetingScheduleParam;
import lombok.NonNull;

public class ScheduleService extends ApiService {
  public ScheduleService(ResponseHandler responseHandler) {
    super(responseHandler);
  }

  public MeetingScheduleCollection createMeetingSchedule(@NonNull String email, MeetingScheduleParam meetingScheduleParam) {
    return createMeetingSchedule(email, meetingScheduleParam, null);
  }

  public MeetingScheduleCollection createMeetingSchedule(@NonNull String email, MeetingScheduleParam meetingScheduleParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/schedules/%s";
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.POST,
        String.format(path, ApiResource.urlEncode(email)),
        ApiRequestParams.paramsToMap(meetingScheduleParam),
        ApiResource.getTypeReference(MeetingScheduleCollection.class),
        requestOptions);
  }

  public void deleteMeetingSchedule(@NonNull String email, DeleteMeetingScheduleParam deleteMeetingScheduleParam) {
    deleteMeetingSchedule(email, deleteMeetingScheduleParam, null);
  }

  public void deleteMeetingSchedule(@NonNull String email, DeleteMeetingScheduleParam deleteMeetingScheduleParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/schedules/%s/%s";
    getResponseHandler()
      .request(
        ApiResource.RequestMethod.DELETE,
        String.format(path, ApiResource.urlEncode(email), ApiResource.urlEncode(deleteMeetingScheduleParam.getMeetingID())),
        null,
        null,
        requestOptions);
  }

  public MeetingScheduleCollection createBreakoutSchedule(@NonNull String email, @NonNull String meetingID, BreakoutScheduleParam breakoutScheduleParam) {
    return createBreakoutSchedule(email, meetingID, breakoutScheduleParam, null);
  }

  public MeetingScheduleCollection createBreakoutSchedule(@NonNull String email, @NonNull String meetingID, BreakoutScheduleParam breakoutScheduleParam, RequestOptions requestOptions) {
    final String path = "/external/api/v1/schedules/%s/%s/breakout";
    return getResponseHandler()
      .request(
        ApiResource.RequestMethod.POST,
        String.format(path, ApiResource.urlEncode(email), ApiResource.urlEncode(meetingID)),
        ApiRequestParams.paramsToMap(breakoutScheduleParam),
        ApiResource.getTypeReference(MeetingScheduleCollection.class),
        requestOptions);
  }
}
