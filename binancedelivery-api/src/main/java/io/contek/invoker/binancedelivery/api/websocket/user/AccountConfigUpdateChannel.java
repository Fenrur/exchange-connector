package io.contek.invoker.binancedelivery.api.websocket.user;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import static io.contek.invoker.binancedelivery.api.websocket.user.constants.UserEventTypeKeys._ACCOUNT_CONFIG_UPDATE;

public final class AccountConfigUpdateChannel
    extends UserWebSocketChannel<
        AccountConfigUpdateChannel.Id, AccountConfigUpdateChannel.Message> {

  AccountConfigUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends UserWebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_ACCOUNT_CONFIG_UPDATE);
    }
  }

  public static final class Message extends WebSocketEventMessage {

    public Long T; // transaction time
    public AccountConfig ac;

    public static class AccountConfig {

      public String s; // symbol
      public Integer l; // leverage
    }
  }
}
