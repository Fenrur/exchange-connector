package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.api.websocket.SubscriptionState;
import io.contek.invoker.commons.api.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.api.websocket.SubscriptionState.*;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketSubscriptionKeys.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
    extends BaseWebSocketChannel<Message> {

  protected abstract String getChannel();

  protected abstract String getMarket();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = subscribe;
    request.channel = getChannel();
    request.market = getMarket();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = unsubscribe;
    request.channel = getChannel();
    request.market = getMarket();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscriptionResponse) {
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (!getChannel().equals(confirmation.channel) || !getMarket().equals(confirmation.market)) {
        return null;
      }
      switch (confirmation.type) {
        case subscribed:
          return SUBSCRIBED;
        case unsubscribed:
          return UNSUBSCRIBED;
        default:
          throw new IllegalArgumentException(confirmation.type);
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
