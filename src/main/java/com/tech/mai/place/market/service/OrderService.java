package com.tech.mai.place.market.service;

import com.tech.mai.place.market.type.Order;

import java.util.List;

public interface OrderService {

  /**
   * Returns all order for a given seller
   * @param sellerId The id of the seller
   * @return A list of orders associated to the seller
   */
  public List<Order> getOrdersBySeller(long sellerId);

  /**
   * Returns all orders for a given buyer
   * @param buyerId The id of the buyer
   * @return A list of orders associated to the buyer
   */
  public List<Order> getOrdersByBuyer(long buyerId);
}
