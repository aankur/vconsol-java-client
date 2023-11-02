package com.techgentsia.model;

import com.techgentsia.net.ResponseHandler;
import lombok.Data;

import java.util.List;

@Data
public class MeetingScheduleCollection extends VconsolObject implements VconsolActiveObject {
  ResponseHandler responseHandler;
  List<MeetingSchedule> meetings;

  @Override
  public void setResponseHandler(ResponseHandler responseHandler) {
    this.responseHandler = responseHandler;
    if (this.meetings != null) {
      for (MeetingSchedule item : meetings) {
        trySetResponseGetter(item, responseHandler);
      }
    }
  }
}
