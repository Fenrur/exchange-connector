package io.contek.invoker.bitmex.api.rest.market;

import io.contek.invoker.bitmex.api.common._OrderBook;
import io.contek.invoker.bitmex.api.rest.market.GetOrderBookL2.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import static com.google.common.base.Preconditions.checkNotNull;

public final class GetOrderBookL2 extends MarketRestRequest<Response> {

  private String symbol;
  private Integer depth;

  GetOrderBookL2(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBookL2 setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrderBookL2 setDepth(Integer depth) {
    this.depth = depth;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/orderBook/L2";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();
    checkNotNull(symbol);
    builder.add("symbol", symbol);
    if (depth != null) {
      builder.add("depth", depth);
    }
    return builder.build();
  }

  public static final class Response extends _OrderBook {}
}
