package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.rest.user.PostPositionRiskLimit.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.commons.rest.RestMethod.POST;

public final class PostPositionRiskLimit extends UserRestRequest<Response> {

  private String symbol;
  private Double riskLimit;

  PostPositionRiskLimit(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionRiskLimit setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionRiskLimit setRiskLimit(Double riskLimit) {
    this.riskLimit = riskLimit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/position/riskLimit";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    checkNotNull(riskLimit);
    builder.add("riskLimit", riskLimit);

    return builder.build();
  }

  public static final class Response extends _Position {}
}
