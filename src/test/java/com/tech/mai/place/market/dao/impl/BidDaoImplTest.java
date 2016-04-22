package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.type.Bid;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BidDaoImplTest extends AbstractDaoImplTest {

  @Override
  @Before
  public void setUp() throws Exception {
    bidDao.clearData();
  }

  @Test
  public void testAddBid(){
    Bid bidA = new Bid(123, 234, 3, new BigDecimal("12.50"));
    Bid bidB = new Bid(123, 235, 5, new BigDecimal("10.00"));
    Bid bidC = new Bid(123, 236, 2, new BigDecimal("15.25"));

    long bidAId = bidDao.addBid(bidA);
    long bidBId = bidDao.addBid(bidB);
    long bidCId = bidDao.addBid(bidC);

    assertTrue(bidAId > 0);
    assertEquals(bidAId + 1, bidBId);
    assertEquals(bidBId + 1, bidCId);
  }

  @Test
  public void testUpdateBid(){
    Bid bidA = new Bid(129, 534, 3, new BigDecimal("19.50"));

    bidA.setBidId(bidDao.addBid(bidA));
    bidA.setQuantity(5);

    assertTrue(bidDao.updateBid(bidA));
  }

  @Test
  public void testGetBidsByBuyer(){
    Bid bidA = new Bid(123, 999, 3, new BigDecimal("12.50"));
    Bid bidB = new Bid(123, 999, 5, new BigDecimal("10.00"));
    Bid bidC = new Bid(123, 111, 2, new BigDecimal("15.25"));

    bidDao.addBid(bidA);
    bidDao.addBid(bidB);
    bidDao.addBid(bidC);

    List<Bid> buyersBids = bidDao.getBids(999);

    assertEquals(2, buyersBids.size());
    for (Bid bid : buyersBids) { assertEquals(999, bid.getUserId()); }
  }

  @Test
  public void testGetHighestBidPrice(){
    Bid bidA = new Bid(391, 901, 3, new BigDecimal("12.50"));
    Bid bidB = new Bid(391, 902, 5, new BigDecimal("20.00"));
    Bid bidC = new Bid(391, 903, 2, new BigDecimal("15.25"));

    bidDao.addBid(bidA);
    bidDao.addBid(bidB);
    bidDao.addBid(bidC);

    BigDecimal highestBidPrice = bidDao.getHighestBidPrice(391);

    assertEquals(0, new BigDecimal("20.00").compareTo(highestBidPrice));
  }

}
