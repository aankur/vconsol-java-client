package com.techgentsia;

import com.techgentsia.net.LiveVconsolResponseHandler;
import com.techgentsia.net.VconsolResponseHandlerOptions;
import com.techgentsia.service.MeetingService;
import com.techgentsia.service.ScheduleService;
import com.techgentsia.service.UserService;

public class VconsolClient {

  LiveVconsolResponseHandler liveVconsolResponseHandler;
  VconsolResponseHandlerOptions handlerOptions;

  public VconsolClient(VconsolResponseHandlerOptions handlerOptions) {
    this.handlerOptions = handlerOptions;
    liveVconsolResponseHandler = new LiveVconsolResponseHandler(handlerOptions, null);
  }

  public UserService users() {
    return new UserService(liveVconsolResponseHandler);
  }

  public ScheduleService schedules() {
    return new ScheduleService(liveVconsolResponseHandler);
  }

  public MeetingService meetings() {
    return new MeetingService(handlerOptions.getApiBase(), liveVconsolResponseHandler);
  }
}
