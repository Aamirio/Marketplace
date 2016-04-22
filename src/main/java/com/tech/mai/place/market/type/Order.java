package com.tech.mai.place.market.type;

import java.math.BigDecimal;

/**
 * Created by aamidr01 on 12/04/2016.
 */
public class Order implements Comparable<Order>{

  private long orderId;
  private long buyerId;
  private long sellerId;
  private long itemId;
  private int quantity;
  private BigDecimal pricePerUnit;

  public Order(long buyerId, long sellerId, long itemId, int quantity, BigDecimal pricePerUnit){
    this.buyerId = buyerId;
    this.sellerId = sellerId;
    this.itemId = itemId;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public long getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(long buyerId) {
    this.buyerId = buyerId;
  }

  public long getSellerId() {
    return sellerId;
  }

  public void setSellerId(long sellerId) {
    this.sellerId = sellerId;
  }

  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
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

  public int compareTo(Order order) {
    if (this.orderId < order.orderId) { return -1; }
    else if (this.orderId > order.orderId) { return 1; }
    else { return 0; }
  }
}
