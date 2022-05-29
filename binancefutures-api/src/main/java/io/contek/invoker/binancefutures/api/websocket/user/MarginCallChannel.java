package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import java.util.List;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys._MARGIN_CALL;

public final class MarginCallChannel
    extends UserWebSocketChannel<MarginCallChannel.Id, MarginCallChannel.Message> {

  MarginCallChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<MarginCallChannel.Message> getMessageType() {
    return MarginCallChannel.Message.class;
  }

  public static final class Id extends UserWebSocketChannelId<MarginCallChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_MARGIN_CALL);
    }
  }

  public static final class Message extends WebSocketEventMessage {

    public Double cw; // transaction time
    public List<PositionForMarginCall> p;

    public static class PositionForMarginCall {

      public String s; // symbol
      public String ps; // position side
      public String mt; // margin type
      public Long iw; // isolated wallet
      public Double mp; // mark price
      public Double up; // unrealized PnL
      public Double mm; // maintenance margin required
    }
  }
}
