package io.contek.invoker.hbdminverse.api.common;

public class _LiquidationOrder {

  public String contract_code;
  public String symbol;
  public String direction;
  public String offset;
  public double volume;
  public long created_at;
  public double amount;
  public double trade_turnover;

  @Override
  public String toString() {
    return "_LiquidationOrder{" +
            "contract_code='" + contract_code + '\'' +
            ", symbol='" + symbol + '\'' +
            ", direction='" + direction + '\'' +
            ", offset='" + offset + '\'' +
            ", volume=" + volume +
            ", created_at=" + created_at +
            ", amount=" + amount +
            ", trade_turnover=" + trade_turnover +
            '}';
  }
}
