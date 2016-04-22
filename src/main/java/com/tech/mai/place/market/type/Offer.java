package com.tech.mai.place.market.type;

import java.math.BigDecimal;

/**
 * Created by Aamir Idrees on 12/04/2016.
 */
public class Offer implements Comparable<Offer> {

  private long offerId;
  private long itemId;
  private long userId;
  private int quantity;
  private BigDecimal pricePerUnit;
  private Status offerStatus;

  public Offer(long itemId, long userId, int quantity, BigDecimal pricePerUnit){
    this.itemId = itemId;
    this.userId = userId;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.offerStatus = Status.VALID;
  }

  public long getOfferId() {
    return offerId;
  }

  public void setOfferId(long offerId) {
    this.offerId = offerId;
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

  public Status getOfferStatus() {
    return offerStatus;
  }

  public void expire() {
    this.offerStatus = Status.EXPIRED;
  }

  public int compareTo(Offer offer) {
    if (this.offerId < offer.offerId) { return -1; }
    else if (this.offerId > offer.offerId) { return 1; }
    else { return 0; }
  }
}
