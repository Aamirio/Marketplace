package com.tech.mai.place.market.type;

import java.math.BigDecimal;

/**
 * Created by Aamir Idrees on 12/04/2016.
 */
public class Bid implements Comparable<Bid>{

  private long bidId;
  private long itemId;
  private long userId;
  private int quantity;
  private BigDecimal pricePerUnit;
  private Status bidStatus;

  public Bid(long itemId, long userId, int quantity, BigDecimal pricePerUnit){
    this.itemId = itemId;
    this.userId = userId;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.bidStatus = Status.VALID;
  }

  public long getBidId() {
    return bidId;
  }

  public void setBidId(long bidId) {
    this.bidId = bidId;
  }

  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPricePerUnit() {
    return pricePerUnit;
  }

  public void setPricePerUnit(BigDecimal pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public Status getBidStatus() {
    return bidStatus;
  }

  public void expire() {
    this.bidStatus = Status.EXPIRED;
  }

  public int compareTo(Bid bid) {
    if (this.bidId < bid.bidId) { return -1; }
    else if (this.bidId > bid.bidId) { return 1; }
    else { return 0; }
  }
}
