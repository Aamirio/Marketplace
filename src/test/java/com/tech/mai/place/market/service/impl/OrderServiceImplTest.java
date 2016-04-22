package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.OrderDao;
import com.tech.mai.place.market.service.impl.OrderServiceImpl;
import com.tech.mai.place.market.type.Order;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest extends TestCase {

  @Mock private OrderDao orderDao;

  @InjectMocks
  private OrderServiceImpl orderService;

  @Test
  public void testGetOrdersBySeller() {
    long sellerId = 111;
    Order orderA = new Order(999, sellerId, 123, 3, new BigDecimal("12.50"));
    Order orderB = new Order(888, sellerId, 234, 3, new BigDecimal("12.50"));
    Order orderC = new Order(777, sellerId, 345, 3, new BigDecimal("12.50"));

    List<Order> orders = new ArrayList<Order>();
    orders.add(orderA);
    orders.add(orderB);
    orders.add(orderC);

    when(orderDao.getOrdersBySeller(any(Long.class))).thenReturn(orders);

    assertEquals(orders, orderService.getOrdersBySeller(sellerId));
  }

  @Test
  public void testGetOrdersByBuyer() {
    long buyerId = 222;
    Order orderA = new Order(buyerId, 777, 123, 3, new BigDecimal("12.50"));
    Order orderB = new Order(buyerId, 888, 234, 3, new BigDecimal("12.50"));
    Order orderC = new Order(buyerId, 999, 345, 3, new BigDecimal("12.50"));

    List<Order> orders = new ArrayList<Order>();
    orders.add(orderA);
    orders.add(orderB);
    orders.add(orderC);

    when(orderDao.getOrdersByBuyer(any(Long.class))).thenReturn(orders);

    assertEquals(orders, orderService.getOrdersByBuyer(buyerId));
  }

}
