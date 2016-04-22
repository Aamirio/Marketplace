package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.service.impl.OfferServiceImpl;
import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
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
public class OfferServiceImplTest extends TestCase {

  @Mock private BidDao bidDao;
  @Mock private OfferDao offerDao;

  @InjectMocks
  private OfferServiceImpl offerService;

  @Test
  public void testAddOffer(){
    long itemId = 123;
    long offerAId = 11;
    Offer offerA = new Offer(itemId, 234, 3, new BigDecimal("12.50"));

    when(offerDao.addOffer(any(Offer.class))).thenReturn(new Long(offerAId));
    when(bidDao.getBidsByItemId(any(Long.class))).thenReturn(new ArrayList<Bid>());

    assertEquals(offerAId, offerService.addOffer(offerA));
  }

  @Test
  public void testGetOffersBySellerId(){
    long sellerUserId = 999;
    Offer offerA = new Offer(123, sellerUserId, 3, new BigDecimal("12.50"));
    Offer offerB = new Offer(124, sellerUserId, 5, new BigDecimal("10.00"));
    Offer offerC = new Offer(125, sellerUserId, 2, new BigDecimal("15.25"));

    List<Offer> expectedOffers = new ArrayList<Offer>();
    expectedOffers.add(offerA);
    expectedOffers.add(offerB);
    expectedOffers.add(offerC);

    when(offerDao.getOffersBySellerId(any(Long.class))).thenReturn(expectedOffers);
    List<Offer> buyersOffers = offerService.getOffersBySellerId(sellerUserId);

    assertEquals(3, buyersOffers.size());
    for (Offer offer : buyersOffers) { assertEquals(999, offer.getUserId()); }
  }

  @Test
  public void testGetLowestOfferPrice(){
    BigDecimal expLowOfferPrice = new BigDecimal("100.12");

    when(offerDao.getLowestOfferPrice(any(Long.class))).thenReturn(expLowOfferPrice);
    BigDecimal lowestOfferPrice = offerService.getLowestOfferPrice(123);

    assertEquals(0, expLowOfferPrice.compareTo(lowestOfferPrice));
  }

}
