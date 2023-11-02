package com.techgentsia.param.meeting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techgentsia.net.ApiRequestParams;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingUrlQueryParams extends ApiRequestParams {
  @JsonProperty("exit_url")
  String exitUrl;
  @JsonProperty("hide_brand_logo")
  Boolean hideBrandLogo;
  @JsonProperty("hide_theme_btn")
  Boolean hideThemeBtn;
  @JsonProperty("hide_reg_btn")
  Boolean hideRegBtn;
  @JsonProperty("hide_cancel_btn")
  Boolean hideCancelBtn;
  @JsonProperty("hide_join_info")
  Boolean hideJoinInfo;
  @JsonProperty("hide_login_link")
  Boolean hideLoginLink;
}
