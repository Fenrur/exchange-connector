package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.common._OrderBookLevel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;

import java.util.List;

public final class DiffOrderBookChannel
    extends WebSocketChannel<DiffOrderBookChannel.Id, DiffOrderBookChannel.Message> {

  public static final String PREFIX = "diff_order_book_";

  DiffOrderBookChannel(DiffOrderBookChannel.Id id) {
    super(id);
  }

  @Override
  public Class<DiffOrderBookChannel.Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<DiffOrderBookChannel.Message> {

    private Id(String currencyPair) {
      super(PREFIX + currencyPair);
    }

    public static Id of(String currencyPair) {
      return new Id(currencyPair);
    }
  }

  public static final class Message extends WebSocketChannelMessage<Data> {}

  public static final class Data {

    public Long timestamp;
    public Long microtimestamp;
    public List<_OrderBookLevel> bids;
    public List<_OrderBookLevel> asks;
  }
}
