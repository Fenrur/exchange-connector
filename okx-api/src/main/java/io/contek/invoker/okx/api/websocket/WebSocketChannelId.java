package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelArg;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

public abstract class WebSocketChannelId<Message extends WebSocketChannelPushData<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;

  protected WebSocketChannelId(String channel, String suffix) {
    super(combine(channel, suffix));
    this.channel = channel;
  }

  private static String combine(String channel, String suffix) {
    return String.join(".", channel, suffix);
  }

  public final String getChannel() {
    return channel;
  }

  public abstract WebSocketChannelArg toChannelArg();
}
