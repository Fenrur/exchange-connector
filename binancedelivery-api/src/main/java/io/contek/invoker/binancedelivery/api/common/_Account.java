package io.contek.invoker.binancedelivery.api.common;

import java.util.List;

public class _Account {

  public Boolean canDeposit;
  public Boolean canTrade;
  public Boolean canWithdraw;
  public Integer feeTier;
  public Long updateTime;
  public List<_AccountAsset> assets;
  public List<_AccountPosition> positions;
}
