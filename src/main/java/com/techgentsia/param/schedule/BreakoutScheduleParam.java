package com.techgentsia.param.schedule;

import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BreakoutScheduleParam extends ApiRequestParams {
  String title;
}
