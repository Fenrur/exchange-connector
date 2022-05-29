package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

public class MarkPriceUpdateEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public Double p; // Mark price
  public Double r; // Funding rate
  public Long T; // Next funding time
}
