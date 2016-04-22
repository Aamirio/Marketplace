package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.OrderDao;
import com.tech.mai.place.market.service.OrderService;
import com.tech.mai.place.market.type.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class OrderServiceImpl implements OrderService {

  @Inject
  OrderDao orderDao;

  /**
   * Returns all orders for a given seller which have not expired
   *
   * @param sellerUserId The id of the seller
   * @return A list of orders associated to the seller which have not expired
   */
  @Override
  public List<Order> getOrdersBySeller(long sellerUserId) {
    List<Order> orders = orderDao.getOrdersBySeller(sellerUserId);
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
    List<Order> orders = orderDao.getOrdersByBuyer(buyerUserId);
    return orders;
  }
}