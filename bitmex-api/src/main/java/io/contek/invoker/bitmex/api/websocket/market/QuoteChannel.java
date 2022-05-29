package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._Quote;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import static java.lang.String.format;

public final class QuoteChannel extends WebSocketChannel<QuoteChannel.Id, QuoteChannel.Message> {

  QuoteChannel(QuoteChannel.Id id) {
    super(id);
  }

  @Override
  public Class<QuoteChannel.Message> getMessageType() {
    return QuoteChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<QuoteChannel.Message> {

    private final String instrument;

    private Id(String instrument) {
      super(format("quote:%s", instrument));
      this.instrument = instrument;
    }

    public static Id of(String instrument) {
      return new Id(instrument);
    }

    @Override
    public boolean accepts(QuoteChannel.Message message) {
      return message.data.stream().map(level -> level.symbol).anyMatch(instrument::equals);
    }
  }

  public static final class Message extends WebSocketTableDataMessage<_Quote> {}
}
