package com.tech.mai.place.market.service;

import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceIT extends AbstractIT {

  @Test
  public void testGetOrdersBySeller() {
    long sellerUserId = 111;
    long buyerUserId = 112;

    Bid bidA = new Bid(124, buyerUserId, 5, new BigDecimal("101.00"));
    Bid bidB = new Bid(124, buyerUserId, 2, new BigDecimal("99.25"));
    Bid bidC = new Bid(123, buyerUserId, 20, new BigDecimal("12.50"));

    Offer offerA = new Offer(124, sellerUserId, 5, new BigDecimal("100.00"));
    Offer offerB = new Offer(124, sellerUserId, 2, new BigDecimal("15.25"));
    Offer offerC = new Offer(123, 998, 20, new BigDecimal("12.50"));

    bidService.addBid(bidA);
    bidService.addBid(bidB);
    bidService.addBid(bidC);

    offerService.addOffer(offerA);
    offerService.addOffer(offerB);
    offerService.addOffer(offerC);

    Order expOrderA = new Order(buyerUserId, sellerUserId, 124, 5, new BigDecimal("100.00"));
    Order expOrderB = new Order(buyerUserId, sellerUserId, 124, 2, new BigDecimal("15.25"));

    List<Order> orders = new ArrayList<Order>();
    orders.add(expOrderA);
    orders.add(expOrderB);

    List<Order> retrievedOrders = orderService.getOrdersBySeller(sellerUserId);

    assertEquals(orders.size(), retrievedOrders.size());

    for (Order order : retrievedOrders) { assertEquals(sellerUserId, order.getSellerId()); }
  }

  @Test
  public void testGetOrdersByBuyer() {
    long sellerUserId = 111;
    long buyerUserId = 112;

    Bid bidA = new Bid(124, buyerUserId, 5, new BigDecimal("101.00"));
    Bid bidB = new Bid(124, buyerUserId, 2, new BigDecimal("99.25"));
    Bid bidC = new Bid(123, 998, 20, new BigDecimal("12.50"));

    Offer offerA = new Offer(124, sellerUserId, 5, new BigDecimal("100.00"));
    Offer offerB = new Offer(124, sellerUserId, 2, new BigDecimal("15.25"));
    Offer offerC = new Offer(123, sellerUserId, 20, new BigDecimal("12.50"));

    bidService.addBid(bidA);
    bidService.addBid(bidB);
    bidService.addBid(bidC);

    offerService.addOffer(offerA);
    offerService.addOffer(offerB);
    offerService.addOffer(offerC);

    Order expOrderA = new Order(buyerUserId, sellerUserId, 124, 5, new BigDecimal("100.00"));
    Order expOrderB = new Order(buyerUserId, sellerUserId, 124, 2, new BigDecimal("15.25"));

    List<Order> orders = new ArrayList<Order>();
    orders.add(expOrderA);
    orders.add(expOrderB);

    List<Order> retrievedOrders = orderService.getOrdersByBuyer(buyerUserId);

    assertEquals(orders.size(), retrievedOrders.size());

    for (Order order : retrievedOrders) { assertEquals(buyerUserId, order.getBuyerId()); }
  }

}
