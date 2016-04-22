package com.tech.mai.place.market.service;

import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BidServiceIT extends AbstractIT {

  @Test
  public void testAddBidNoOrderRaised(){
    long buyerId = 234;
    Bid bidA = new Bid(123, buyerId, 3, new BigDecimal("12.50"));

    assertTrue(bidService.addBid(bidA) > 0);
    assertEquals(0, orderService.getOrdersByBuyer(buyerId).size());
  }

  @Test
  public void testAddBidAndOrderRaised(){
    long buyerId = 234;
    long itemId = 124;
    long sellerId = 246;
    int quantity = 11;
    BigDecimal pricePerUnit = new BigDecimal("12.00");

    Offer offerA = new Offer(itemId, 245, 10, pricePerUnit);
    Offer offerB = new Offer(itemId, sellerId, 15, pricePerUnit);
    Offer offerC = new Offer(125, 247, 20, pricePerUnit);
    Bid bidA = new Bid(itemId, buyerId, quantity, new BigDecimal("12.50"));
    Bid bidB = new Bid(123, buyerId, 3, new BigDecimal("12.50"));

    offerService.addOffer(offerA);
    offerService.addOffer(offerB);
    offerService.addOffer(offerC);
    bidService.addBid(bidA);
    bidService.addBid(bidB);

    //Check order was raised correctly
    List<Order> newOrders = orderService.getOrdersByBuyer(buyerId);
    assertEquals(1, newOrders.size());

    Order newOrder = newOrders.get(0);
    assertEquals(itemId, newOrder.getItemId());
    assertEquals(sellerId, newOrder.getSellerId());
    assertEquals(buyerId, newOrder.getBuyerId());
    assertEquals(quantity, newOrder.getQuantity());
    assertEquals(pricePerUnit, newOrder.getPricePerUnit());

    //Check bid was expired due to order being raised
    List<Bid> bids = bidService.getBids(buyerId);
    assertEquals(1, bids.size());

    //Check offer state was updated correctly
    List<Offer> offers = offerService.getOffersBySellerId(sellerId);
    assertEquals(1, offers.size());

    Offer offer = offers.get(0);
    assertEquals(itemId, offer.getItemId());
    assertEquals(sellerId, offer.getUserId());
    assertEquals(15 - quantity, offer.getQuantity());
  }

  @Test
  public void testGetBidsByBuyer(){
    long buyerUserId = 999;
    Bid bidA = new Bid(123, buyerUserId, 3, new BigDecimal("12.50"));
    Bid bidB = new Bid(124, buyerUserId, 5, new BigDecimal("10.00"));
    Bid bidC = new Bid(125, 998, 2, new BigDecimal("15.25"));

    bidService.addBid(bidA);
    bidService.addBid(bidB);
    bidService.addBid(bidC);

    List<Bid> expectedBids = new ArrayList<Bid>();
    expectedBids.add(bidA);
    expectedBids.add(bidB);

    List<Bid> bids = bidService.getBids(buyerUserId);

    assertEquals(expectedBids, bids);
  }

  @Test
  public void testGetHighestBidPrice(){
    long itemId = 999;
    Bid bidA = new Bid(itemId, 697, 3, new BigDecimal("1.50"));
    Bid bidB = new Bid(itemId, 698, 5, new BigDecimal("2.00"));
    Bid bidC = new Bid(998, 699, 2, new BigDecimal("5.25"));

    bidService.addBid(bidA);
    bidService.addBid(bidB);
    bidService.addBid(bidC);

    BigDecimal highestBidPrice = bidService.getHighestBidPrice(itemId);

    assertEquals(0, bidB.getPricePerUnit().compareTo(highestBidPrice));
  }

}
