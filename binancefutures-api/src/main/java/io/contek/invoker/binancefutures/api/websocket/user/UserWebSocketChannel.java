package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;

public abstract class UserWebSocketChannel<
        Id extends UserWebSocketChannelId<Message>, Message extends WebSocketEventMessage>
    extends BaseWebSocketChannel<Id, Message> {

  public UserWebSocketChannel(Id id) {
    super(id);
  }

  // We do no action during the subscription phase since the data will be pushed to our end when
  // opening the web socket connection.
  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    return SUBSCRIBED;
  }

  // We do no action in unsubscription phase since all the user-related channel shared the same
  // underlying web socket connection, and there is no way to unsubscribe to
  // io.contek.invoker.ftx.api.a given topic. Either
  // all user related events will be pushed to us, or the connection is closed altogether.
  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    return UNSUBSCRIBED;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof UserDataStreamExpiredEvent) {
      return UNSUBSCRIBED;
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
