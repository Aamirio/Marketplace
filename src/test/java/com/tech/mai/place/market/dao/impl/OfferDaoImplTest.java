package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.type.Offer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OfferDaoImplTest extends AbstractDaoImplTest {

  @Override
  @Before
  public void setUp() throws Exception {
    offerDao.clearData();
  }

  @Test
  public void testAddOffer(){
    Offer offerA = new Offer(123, 234, 3, new BigDecimal("12.50"));
    Offer offerB = new Offer(123, 235, 5, new BigDecimal("10.00"));
    Offer offerC = new Offer(123, 236, 2, new BigDecimal("15.25"));

    long offerAId = offerDao.addOffer(offerA);
    long offerBId = offerDao.addOffer(offerB);
    long offerCId = offerDao.addOffer(offerC);

    assertTrue(offerAId > 0);
    assertEquals(offerAId + 1, offerBId);
    assertEquals(offerBId + 1, offerCId);

  }

  @Test
  public void testUpdateOffer(){
    Offer offerA = new Offer(129, 534, 3, new BigDecimal("19.50"));

    offerA.setOfferId(offerDao.addOffer(offerA));
    offerA.setQuantity(5);

    assertTrue(offerDao.updateOffer(offerA));
  }

  @Test
  public void testGetOffersBySellerId(){
    Offer offerA = new Offer(123, 323, 3, new BigDecimal("12.50"));
    Offer offerB = new Offer(123, 323, 5, new BigDecimal("10.00"));
    Offer offerC = new Offer(123, 313, 2, new BigDecimal("15.25"));

    offerDao.addOffer(offerA);
    offerDao.addOffer(offerB);
    offerDao.addOffer(offerC);

    List<Offer> sellersOffers = offerDao.getOffersBySellerId(323);

    assertEquals(2, sellersOffers.size());
    for (Offer offer : sellersOffers) { assertEquals(323, offer.getUserId()); }

  }

  @Test
  public void testGetLowestOfferPrice(){

    Offer offerA = new Offer(717, 911, 3, new BigDecimal("20.50"));
    Offer offerB = new Offer(717, 912, 3, new BigDecimal("9.00"));
    Offer offerC = new Offer(717, 913, 3, new BigDecimal("12.50"));

    offerDao.addOffer(offerA);
    offerDao.addOffer(offerB);
    offerDao.addOffer(offerC);

    BigDecimal lowestPrice = offerDao.getLowestOfferPrice(717);

    assertEquals(0, new BigDecimal("9.00").compareTo(lowestPrice));
  }

}
