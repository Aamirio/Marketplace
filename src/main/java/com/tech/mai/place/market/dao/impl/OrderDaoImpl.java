package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.dao.OrderDao;
import com.tech.mai.place.market.type.Order;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class OrderDaoImpl implements OrderDao {

  private static Map<Long, Order> allOrders = new HashMap<Long, Order>();

  /**
   * Adds an order
   *
   * @param order The order
   */
  @Override
  public long addOrder(Order order) {
    order.setOrderId(allOrders.size() + 1);
    allOrders.put(order.getOrderId(), order);
    return order.getOrderId();
  }

  /**
   * Returns all orders for a given seller which have not expired
   *
   * @param sellerUserId The id of the seller
   * @return A list of orders associated to the seller which have not expired
   */
  @Override
  public List<Order> getOrdersBySeller(long sellerUserId) {
    List<Order> orders = new ArrayList<>();

    for (Order order : allOrders.values()) {
      if (order.getSellerId() == sellerUserId) { orders.add(order); }
    }
    return orders;
  }

  /**
   * Returns all orders for a given buyer
   *
   * @param buyerUserId The id of the buyer
   * @return A list of orders associated to the buyer which have not expired
   */
  @Override
  public List<Order> getOrdersByBuyer(long buyerUserId) {
    List<Order> orders = new ArrayList<>();

    for (Order order : allOrders.values()) {
      if (order.getBuyerId() == buyerUserId) { orders.add(order); }
    }
    return orders;
  }

  /**
   * Removes all existing orders
   */
  @Override
  public void clearData() {
    allOrders.clear();
  }
}
