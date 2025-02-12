package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._Trade;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import static java.lang.String.format;

public final class TradeChannel extends WebSocketChannel<TradeChannel.Id, TradeChannel.Message> {

  TradeChannel(TradeChannel.Id id) {
    super(id);
  }

  @Override
  public Class<TradeChannel.Message> getMessageType() {
    return TradeChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<TradeChannel.Message> {

    private final String instrument;

    private Id(String instrument) {
      super(format("trade:%s", instrument));
      this.instrument = instrument;
    }

    public static Id of(String instrument) {
      return new Id(instrument);
    }

    @Override
    public boolean accepts(TradeChannel.Message message) {
      return message.data.stream().map(trade -> trade.symbol).anyMatch(instrument::equals);
    }
  }

  public static final class Message extends WebSocketTableDataMessage<_Trade> {}
}
