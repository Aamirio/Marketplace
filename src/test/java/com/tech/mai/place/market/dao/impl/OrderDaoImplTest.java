package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.type.Order;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoImplTest extends AbstractDaoImplTest {

  @Override
  @Before
  public void setUp() throws Exception {
    orderDao.clearData();
  }

  @Test
  public void testAddOrder() {
    Order orderA = new Order(123, 234, 401, 9, new BigDecimal("12.50"));
    Order orderB = new Order(234, 345, 401, 19, new BigDecimal("10.00"));
    Order orderC = new Order(345, 456, 401, 29, new BigDecimal("15.25"));

    long orderAId = orderDao.addOrder(orderA);
    long orderBId = orderDao.addOrder(orderB);
    long orderCId = orderDao.addOrder(orderC);

    assertTrue(orderAId > 0);
    assertEquals(orderAId + 1, orderBId);
    assertEquals(orderBId + 1, orderCId);
  }

  @Test
  public void testGetOrdersBySeller() {
    Order orderA = new Order(123, 234, 411, 9, new BigDecimal("12.50"));
    Order orderB = new Order(234, 959, 411, 9, new BigDecimal("10.00"));
    Order orderC = new Order(345, 959, 412, 9, new BigDecimal("15.25"));

    long orderAId = orderDao.addOrder(orderA);
    long orderBId = orderDao.addOrder(orderB);
    long orderCId = orderDao.addOrder(orderC);

    List<Order> sellersOrders = orderDao.getOrdersBySeller(959);

    assertEquals(2, sellersOrders.size());

    for (Order order : sellersOrders) {
      assertTrue(order.getOrderId() == orderBId || order.getOrderId() == orderCId);
    }
  }

  @Test
  public void testGetOrdersByBuyer() {
    Order orderA = new Order(123, 234, 411, 9, new BigDecimal("12.50"));
    Order orderB = new Order(512, 511, 411, 9, new BigDecimal("10.00"));
    Order orderC = new Order(512, 511, 412, 9, new BigDecimal("15.25"));

    long orderAId = orderDao.addOrder(orderA);
    long orderBId = orderDao.addOrder(orderB);
    long orderCId = orderDao.addOrder(orderC);

    List<Order> sellersOrders = orderDao.getOrdersByBuyer(512);

    assertEquals(2, sellersOrders.size());

    for (Order order : sellersOrders) {
      assertTrue(order.getOrderId() == orderBId || order.getOrderId() == orderCId);
    }
  }

}
