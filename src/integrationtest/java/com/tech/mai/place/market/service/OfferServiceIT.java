package com.tech.mai.place.market.service;

import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferServiceIT extends AbstractIT {

  @Test
  public void testAddOfferNoOfferRaised(){
    long sellerId = 234;
    Offer offerA = new Offer(123, 234, 3, new BigDecimal("12.50"));

    assertTrue(offerService.addOffer(offerA) > 0);
    assertEquals(0, orderService.getOrdersByBuyer(sellerId).size());
  }

  @Test
  public void testAddOfferAndOrderRaised(){
    long buyerId = 234;
    long itemId = 124;
    long sellerId = 246;
    int quantity = 15;
    BigDecimal pricePerUnit = new BigDecimal("12.00");

    Bid bidA = new Bid(123, buyerId, 10, new BigDecimal("12.50"));
    Bid bidB = new Bid(itemId, buyerId, quantity, new BigDecimal("12.50"));
    Bid bidC = new Bid(125, buyerId, 20, new BigDecimal("12.50"));
    Offer offerA = new Offer(itemId, sellerId, 16, pricePerUnit);
    Offer offerB = new Offer(126, 247, 11, new BigDecimal("12.00"));

    bidService.addBid(bidA);
    bidService.addBid(bidB);
    bidService.addBid(bidC);
    offerService.addOffer(offerA);
    offerService.addOffer(offerB);

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
    assertEquals(2, bids.size());

    //Check offer state was updated correctly
    List<Offer> offers = offerService.getOffersBySellerId(sellerId);
    assertEquals(1, offers.size());

    Offer offer = offers.get(0);
    assertEquals(itemId, offer.getItemId());
    assertEquals(sellerId, offer.getUserId());
    assertEquals(16 - quantity, offer.getQuantity());
  }

  @Test
  public void testGetOffersBySellerId(){
    long sellerUserId = 999;
    Offer offerA = new Offer(123, 998, 3, new BigDecimal("12.50"));
    Offer offerB = new Offer(124, sellerUserId, 5, new BigDecimal("10.00"));
    Offer offerC = new Offer(125, sellerUserId, 2, new BigDecimal("15.25"));

    offerService.addOffer(offerA);
    offerService.addOffer(offerB);
    offerService.addOffer(offerC);

    List<Offer> expectedOffers = new ArrayList<Offer>();
    expectedOffers.add(offerB);
    expectedOffers.add(offerC);

    assertEquals(expectedOffers, offerService.getOffersBySellerId(sellerUserId));
  }

  @Test
  public void testGetLowestOfferPrice(){
    long itemId = 276;
    Offer offerA = new Offer(itemId, 197, 3, new BigDecimal("17.50"));
    Offer offerB = new Offer(itemId, 198, 5, new BigDecimal("20.00"));
    Offer offerC = new Offer(275, 199, 2, new BigDecimal("15.25"));

    offerService.addOffer(offerA);
    offerService.addOffer(offerB);
    offerService.addOffer(offerC);

    assertEquals(0, offerA.getPricePerUnit().compareTo(offerService.getLowestOfferPrice(itemId)));
  }

}
