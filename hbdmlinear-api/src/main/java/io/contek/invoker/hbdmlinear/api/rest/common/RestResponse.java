package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestResponse {

  public String status;
  public long ts;
}
