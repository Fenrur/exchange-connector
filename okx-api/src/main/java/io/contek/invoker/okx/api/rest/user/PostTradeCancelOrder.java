package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._CancelOrderAck;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

public final class PostTradeCancelOrder extends UserRestRequest<PostTradeCancelOrder.Response> {

  public static final RateLimitRule RATE_LIMIT_RULE =
      RateLimitRule.newBuilder()
          .setName("api_key_rest_post_trade_cancel_order")
          .setType(API_KEY)
          .setMaxPermits(60)
          .setResetPeriod(Duration.ofSeconds(2))
          .build();

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(RATE_LIMIT_RULE.forPermits(1));

  private String instId;
  private String ordId;
  private String clOrdId;

  PostTradeCancelOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostTradeCancelOrder setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public PostTradeCancelOrder setOrdId(String ordId) {
    this.ordId = ordId;
    return this;
  }

  public PostTradeCancelOrder setClOrdId(String clOrdId) {
    this.clOrdId = clOrdId;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/cancel-order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instId);
    builder.add("instId", instId);

    if (ordId != null) {
      builder.add("ordId", ordId);
    } else {
      requireNonNull(clOrdId);
      builder.add("clOrdId", clOrdId);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  public static final class Response extends RestResponse<_CancelOrderAck> {}
}
